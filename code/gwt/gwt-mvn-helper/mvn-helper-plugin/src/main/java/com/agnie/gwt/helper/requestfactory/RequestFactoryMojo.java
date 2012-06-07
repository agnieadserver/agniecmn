package com.agnie.gwt.helper.requestfactory;

import java.io.File;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.DirectoryScanner;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.Type;

/**
 * Goal which generate Request Factory interfaces.
 * 
 * @goal requestFactory
 * @phase generate-sources
 * @requiresDependencyResolution compile
 * @version $Id$
 */
public class RequestFactoryMojo extends AbstractMojo {

	protected final static String		IMP_RF_INSTANCE_REQUEST		= "com.google.web.bindery.requestfactory.shared.InstanceRequest";
	protected final static String		IMP_RF_REQUEST				= "com.google.web.bindery.requestfactory.shared.Request";
	protected final static String		IMP_RF_REQUEST_CONTEXT		= "com.google.web.bindery.requestfactory.shared.RequestContext";
	protected final static String		IMP_RF_SERVICE				= "com.google.web.bindery.requestfactory.shared.Service";
	protected final static String		IMP_RF_PROXY_FOR			= "com.google.web.bindery.requestfactory.shared.ProxyFor";
	protected final static String		IMP_RF_VALUE_PROXY			= "com.google.web.bindery.requestfactory.shared.ValueProxy";
	protected final static String		MARKER_RF_ENTITY_PROXY		= "com.agnie.gwt.helper.requestfactory.marker.RFEntityProxy";
	protected final static String		MARKER_RF_VALUE_PROXY		= "com.agnie.gwt.helper.requestfactory.marker.RFValueProxy";
	protected final static String		MARKER_RF_SERVICE_METHOD	= "com.agnie.gwt.helper.requestfactory.marker.RFServiceMethod";
	protected final static String		MARKER_RF_PROXY_METHOD		= "com.agnie.gwt.helper.requestfactory.marker.RFProxyMethod";
	protected final static String		MARKER_RF_SERVICE			= "com.agnie.gwt.helper.requestfactory.marker.RFService";

	final static Set<String>			basicDataTypes				= new HashSet<String>();
	static {
		basicDataTypes.add("boolean");
		basicDataTypes.add("Boolean");
		basicDataTypes.add("byte");
		basicDataTypes.add("Byte");
		basicDataTypes.add("char");
		basicDataTypes.add("Character");
		basicDataTypes.add("short");
		basicDataTypes.add("Short");
		basicDataTypes.add("int");
		basicDataTypes.add("Integer");
		basicDataTypes.add("long");
		basicDataTypes.add("Long");
		basicDataTypes.add("float");
		basicDataTypes.add("Float");
		basicDataTypes.add("double");
		basicDataTypes.add("Double");
		basicDataTypes.add("void");
		basicDataTypes.add("Void");
		basicDataTypes.add("String");
	}

	/**
	 * @parameter expression="${plugin.version}"
	 * @required
	 * @readonly
	 */
	private String						version;

	/**
	 * @parameter expression="${plugin.artifacts}"
	 * @required
	 * @readonly
	 */
	private Collection<Artifact>		pluginArtifacts;

	/**
	 * @component
	 */
	protected ArtifactResolver			resolver;

	/**
	 * @component
	 */
	protected ArtifactFactory			artifactFactory;

	// --- Some MavenSession related structures --------------------------------

	/**
	 * @parameter expression="${localRepository}"
	 * @required
	 * @readonly
	 */
	protected ArtifactRepository		localRepository;

	/**
	 * @parameter expression="${project.remoteArtifactRepositories}"
	 * @required
	 * @readonly
	 */
	protected List<ArtifactRepository>	remoteRepositories;

	/**
	 * @component
	 */
	protected ArtifactMetadataSource	artifactMetadataSource;

	/**
	 * The maven project descriptor
	 * 
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject				project;

	// --- Plugin parameters ---------------------------------------------------

	/**
	 * Folder where generated-source will be created (automatically added to compile classpath).
	 * 
	 * @parameter default-value="${project.build.directory}/generated-sources/gwt"
	 * @required
	 */
	private File						generateDirectory;

	/**
	 * Stop the build on error
	 * 
	 * @parameter default-value="true" expression="${maven.gwt.failOnError}"
	 */
	private boolean						failOnError;

	/**
	 * Path to include while scanning java classes
	 * 
	 * @parameter default-value=""
	 */
	private List<String>				includePattern;

	/**
	 * Destination overlyType package
	 * 
	 * @parameter default-value=""
	 */
	private String						targetProxyPackage;

	/**
	 * Destination overlyType packageincludePattern
	 * 
	 * @parameter default-value=""
	 */
	private String						targetServicePackage;

	/**
	 * Pattern for GWT service interface
	 * 
	 * @parameter default-value="false" expression="${generateAsync.force}"
	 */
	private boolean						force;

	/**
	 * @parameter expression="${project.build.sourceEncoding}"
	 */
	private String						encoding;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void execute() throws MojoExecutionException {
		getLog().debug("GenerateRequestFactory#execute()");

		if ("pom".equals(project.getPackaging())) {
			getLog().info("GWT Generate Request Factory is skipped");
			return;
		}

		if (encoding == null) {
			getLog().warn("Encoding is not set, your build will be platform dependent");
			encoding = Charset.defaultCharset().name();
		}

		JavaDocBuilder builder = createJavaDocBuilder();

		List<String> sourceRoots = project.getCompileSourceRoots();
		boolean generated = false;
		for (String sourceRoot : sourceRoots) {
			try {
				generated |= scanAndGenerateRequestFactory(new File(sourceRoot), builder);
			} catch (Throwable e) {
				getLog().error("Failed to generate Request Factory interface/s", e);
				if (failOnError) {
					throw new MojoExecutionException("Failed to generate Request Factory interface/s", e);
				}
			}
		}
		if (generated) {
			getLog().debug("add compile source root " + getGenerateDirectory());
			project.addCompileSourceRoot(getGenerateDirectory().getAbsolutePath());
		}
	}

	/**
	 * @param sourceRoot
	 *            the base directory to scan for Entities and service classes
	 * @return true if some file have been generated
	 * @throws Exception
	 *             generation failure
	 */
	private boolean scanAndGenerateRequestFactory(File sourceRoot, JavaDocBuilder builder) throws Exception {
		DirectoryScanner scanner = new DirectoryScanner();
		scanner.setBasedir(sourceRoot);
		if (includePattern != null) {
			scanner.setIncludes(includePattern.toArray(new String[0]));
		}
		scanner.scan();
		String[] sources = scanner.getIncludedFiles();
		if (sources.length == 0) {
			return false;
		}
		boolean fileGenerated = false;
		for (String source : sources) {
			File sourceFile = new File(sourceRoot, source);

			String className = getTopLevelClassName(source);
			JavaClass clazz = builder.getClassByName(className);
			if (clazz.isPublic() && !(clazz.isEnum()) && !(clazz.isInterface())) {
				for (Annotation an : clazz.getAnnotations()) {
					if (an.getType().getJavaClass().isA(MARKER_RF_VALUE_PROXY)) {
						File targetFile = getTargetFile(source, RFType.VALUE_PROXY);
						if (isUpToDate(sourceFile, targetFile)) {
							getLog().debug(targetFile.getAbsolutePath() + " is up to date. Generation skipped");
							// up to date, but still need to report generated-sources
							// directory as sourceRoot
						} else {
							targetFile.getParentFile().mkdirs();
							generateValueProxy(clazz, targetFile);
						}
						fileGenerated = true;
						break;
					} else if (an.getType().getJavaClass().isA(MARKER_RF_ENTITY_PROXY)) {
						File targetFile = getTargetFile(source, RFType.ENTITY_PROXY);
						if (isUpToDate(sourceFile, targetFile)) {
							getLog().debug(targetFile.getAbsolutePath() + " is up to date. Generation skipped");
							// up to date, but still need to report generated-sources
							// directory as sourceRoot
						} else {
							targetFile.getParentFile().mkdirs();
							// generateEntityProxy(clazz, targetFile);
						}
						targetFile = getTargetFile(source, RFType.ENTITY_REQUEST);
						if (isUpToDate(sourceFile, targetFile)) {
							getLog().debug(targetFile.getAbsolutePath() + " is up to date. Generation skipped");
							// up to date, but still need to report generated-sources
							// directory as sourceRoot
						} else {
							targetFile.getParentFile().mkdirs();
							// generateEntityRequest(clazz, targetFile);
						}
						fileGenerated = true;
						break;
					} else if (an.getType().getJavaClass().isA(MARKER_RF_SERVICE)) {
						File targetFile = getTargetFile(source, RFType.SERVICE_REQUEST);
						if (isUpToDate(sourceFile, targetFile)) {
							getLog().debug(targetFile.getAbsolutePath() + " is up to date. Generation skipped");
							// up to date, but still need to report generated-sources
							// directory as sourceRoot
						} else {
							targetFile.getParentFile().mkdirs();
							// generateServiceRequest(clazz, targetFile);
						}
						fileGenerated = true;
						break;
					}
				}
			}
		}
		return fileGenerated;
	}

	private boolean isUpToDate(File sourceFile, File targetFile) {
		return !force && targetFile.exists() && targetFile.lastModified() > sourceFile.lastModified();
	}

	private File getTargetFile(String source, RFType type) throws Exception {
		String targetPackage = "";
		switch (type) {
		case ENTITY_PROXY:
			targetPackage = targetProxyPackage;
			break;
		case VALUE_PROXY:
			targetPackage = targetProxyPackage;
			break;
		case ENTITY_REQUEST:
		case SERVICE_REQUEST:
			targetPackage = targetServicePackage;
			break;

		}
		if (targetPackage != null && !("".equals(targetPackage))) {
			int lastIndex = source.lastIndexOf(File.separatorChar);
			String className = source.substring(lastIndex, source.length() - 5) + type.getPostFix() + ".java";
			String targetFileName = targetPackage.replace('.', File.separatorChar) + className;
			File targetFile = new File(getGenerateDirectory(), targetFileName);
			return targetFile;
		}
		throw new MojoExecutionException("Either <targetProxyPackage> or <targetServicePackage> configuration is missing. Both are required configuration parameters");
	}

	/**
	 * @param clazz
	 *            POJO or bean class from which Value Proxy interface will be generated
	 * @param targetFile
	 *            Value Proxy Interface which will be generated
	 * @throws Exception
	 *             generation failure
	 */
	private void generateValueProxy(JavaClass clazz, File targetFile) throws Exception {
		PrintWriter writer = new PrintWriter(targetFile, encoding);
		if (targetProxyPackage != null && !("".equals(targetProxyPackage))) {
			writer.println("package " + targetProxyPackage + ";");
			writer.println();
		} else {
			throw new MojoExecutionException("Either <targetProxyPackage> configuration is missing.");
		}
		writer.println("import " + IMP_RF_VALUE_PROXY + ";");
		writer.println("import " + IMP_RF_PROXY_FOR + ";");

		/*
		 * TODO: Generate javadoc comments which will tell end developer not to edit the file as it generated file.
		 */
		writer.println();
		writer.println("@ProxyFor(" + clazz.getFullyQualifiedName() + ".class)");
		writer.println("public interface " + clazz.getName() + RFType.VALUE_PROXY.getPostFix() + " extends ValueProxy {");
		JavaMethod[] methods = clazz.getMethods();
		for (JavaMethod method : methods) {
			for (Annotation flAn : method.getAnnotations()) {
				if (flAn.getType().getJavaClass().isA(MARKER_RF_PROXY_METHOD)) {
					writer.print("	" + getMappedType(method.getReturnType()) + " ");
					writer.print("	" + method.getName() + "(");
					JavaParameter parameters[] = method.getParameters();
					if (parameters != null && parameters.length > 0) {
						boolean first = true;
						for (JavaParameter param : parameters) {
							if (first) {
								first = false;
							} else {
								writer.print(",");
							}
							writer.print(" " + getMappedType(param.getType()) + " " + param.getName());
						}
					}
					writer.println(");");
				}
			}
		}

		writer.println("}");
		writer.close();
	}

	private String getMappedType(Type type) throws Exception {

		JavaClass jvCls = type.getJavaClass();
		if (basicDataTypes.contains(jvCls.getName())) {
			return jvCls.getName();
		} else if (isEligibleForGeneration(jvCls)) {
			return jvCls.getName() + "JS";
		} else {
			throw new MojoExecutionException(jvCls.getFullyQualifiedName() + " is niether basic java data type nor it is OverlayType");
		}
	}

	private boolean isEligibleForGeneration(JavaClass javaClass) {
		for (Annotation an : javaClass.getAnnotations()) {
			if (an.getType().getJavaClass().isA(MARKER_RF_VALUE_PROXY) || an.getType().getJavaClass().isA(MARKER_RF_ENTITY_PROXY) || an.getType().getJavaClass().isA(MARKER_RF_SERVICE)
					&& javaClass.isPublic()) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private JavaDocBuilder createJavaDocBuilder() throws MojoExecutionException {
		try {
			JavaDocBuilder builder = new JavaDocBuilder();
			builder.setEncoding(encoding);
			builder.getClassLibrary().addClassLoader(getProjectClassLoader());
			for (String sourceRoot : (List<String>) project.getCompileSourceRoots()) {
				builder.getClassLibrary().addSourceFolder(new File(sourceRoot));
			}
			return builder;
		} catch (MalformedURLException e) {
			throw new MojoExecutionException("Failed to resolve project classpath", e);
		} catch (DependencyResolutionRequiredException e) {
			throw new MojoExecutionException("Failed to resolve project classpath", e);
		}
	}

	private String getTopLevelClassName(String sourceFile) {
		String className = sourceFile.substring(0, sourceFile.length() - 5); // strip
		// ".java"
		return className.replace(File.separatorChar, '.');
	}

	/**
	 * @return the project classloader
	 * @throws DependencyResolutionRequiredException
	 *             failed to resolve project dependencies
	 * @throws MalformedURLException
	 *             configuration issue ?
	 */
	protected ClassLoader getProjectClassLoader() throws DependencyResolutionRequiredException, MalformedURLException {
		getLog().debug("AbstractMojo#getProjectClassLoader()");

		List<?> compile = project.getCompileClasspathElements();
		URL[] urls = new URL[compile.size()];
		int i = 0;
		for (Object object : compile) {
			if (object instanceof Artifact) {
				urls[i] = ((Artifact) object).getFile().toURI().toURL();
			} else {
				urls[i] = new File((String) object).toURI().toURL();
			}
			i++;
		}
		return new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
	}

	public File getGenerateDirectory() {
		if (!generateDirectory.exists()) {
			getLog().debug("Creating target directory " + generateDirectory.getAbsolutePath());
			generateDirectory.mkdirs();
		}
		return generateDirectory;
	}

	private static enum RFType {
		ENTITY_PROXY("Px"), VALUE_PROXY("Vpx"), ENTITY_REQUEST("Request"), SERVICE_REQUEST("Request");

		String	postFix;

		private RFType(String postFix) {
			this.postFix = postFix;
		}

		/**
		 * @return the postFix
		 */
		public String getPostFix() {
			return postFix;
		}

	}

}

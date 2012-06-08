package com.agnie.gwt.helper.requestfactory;

import java.io.File;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
/*
 * TODO: Handle templated parameters or return type. Handle Array, list and set type.
 */
public class RequestFactoryMojo extends AbstractMojo {

	protected final static String		IMP_RF_INSTANCE_REQUEST		= "com.google.web.bindery.requestfactory.shared.InstanceRequest";
	protected final static String		IMP_RF_REQUEST				= "com.google.web.bindery.requestfactory.shared.Request";
	protected final static String		IMP_RF_REQUEST_CONTEXT		= "com.google.web.bindery.requestfactory.shared.RequestContext";
	protected final static String		IMP_RF_SERVICE				= "com.google.web.bindery.requestfactory.shared.Service";
	protected final static String		IMP_RF_PROXY_FOR			= "com.google.web.bindery.requestfactory.shared.ProxyFor";
	protected final static String		IMP_RF_VALUE_PROXY			= "com.google.web.bindery.requestfactory.shared.ValueProxy";
	protected final static String		IMP_RF_ENTITY_PROXY			= "com.google.web.bindery.requestfactory.shared.EntityProxy";
	protected final static String		IMP_RF_ENTITY_PROXY_ID		= "com.google.web.bindery.requestfactory.shared.EntityProxyId";
	protected final static String		MARKER_RF_ENTITY_PROXY		= "com.agnie.gwt.helper.requestfactory.marker.RFEntityProxy";
	protected final static String		MARKER_RF_VALUE_PROXY		= "com.agnie.gwt.helper.requestfactory.marker.RFValueProxy";
	protected final static String		MARKER_RF_SERVICE_METHOD	= "com.agnie.gwt.helper.requestfactory.marker.RFServiceMethod";
	protected final static String		MARKER_RF_PROXY_METHOD		= "com.agnie.gwt.helper.requestfactory.marker.RFProxyMethod";
	protected final static String		MARKER_RF_SERVICE			= "com.agnie.gwt.helper.requestfactory.marker.RFService";

	final static Map<String, String>	wrappers					= new HashMap<String, String>();

	static {
		wrappers.put("int", "Integer");
		wrappers.put("long", "Long");
		wrappers.put("double", "Double");
		wrappers.put("float", "Float");
		wrappers.put("byte", "Byte");
		wrappers.put("char", "Character");
		wrappers.put("short", "Short");
		wrappers.put("boolean", "Boolean");
		wrappers.put("void", "Void");
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
	 * 
	 * @parameter default-value="true"
	 */
	private boolean						generateStableId;

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
							generateValueProxy(clazz, targetFile, builder);
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
							generateEntityProxy(clazz, targetFile, builder);
						}
						targetFile = getTargetFile(source, RFType.ENTITY_REQUEST);
						if (isUpToDate(sourceFile, targetFile)) {
							getLog().debug(targetFile.getAbsolutePath() + " is up to date. Generation skipped");
							// up to date, but still need to report generated-sources
							// directory as sourceRoot
						} else {
							targetFile.getParentFile().mkdirs();
							generateEntityRequest(clazz, targetFile, builder);
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
							generateServiceRequest(clazz, targetFile, builder);
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
	 *            Manager or/ service class from which Service Request interface will be generated
	 * @param targetFile
	 *            Service Interface which will be generated
	 * @throws Exception
	 *             generation failure
	 */
	private void generateServiceRequest(JavaClass clazz, File targetFile, JavaDocBuilder builder) throws Exception {
		PrintWriter writer = new PrintWriter(targetFile, encoding);
		if (targetServicePackage != null && !("".equals(targetServicePackage))) {
			writer.println("package " + targetServicePackage + ";");
			writer.println();
		} else {
			throw new MojoExecutionException("<targetServicePackage> configuration is missing.");
		}
		writer.println("import " + IMP_RF_REQUEST + ";");
		writer.println("import " + IMP_RF_REQUEST_CONTEXT + ";");
		writer.println("import " + IMP_RF_SERVICE + ";");
		writer.println();
		writer.println(" /* Generated type. dont change the contents */");
		writer.println();
		/*
		 * TODO: Generate javadoc comments which will tell end developer not to edit the file as it generated file.
		 */
		writer.println();
		for (Annotation an : clazz.getAnnotations()) {
			if (an.getType().getJavaClass().isA(MARKER_RF_SERVICE)) {
				writer.println("@Service(value =" + clazz.getFullyQualifiedName() + ".class, locator = " + an.getNamedParameter("value") + ")");
				break;
			}
		}
		String targetClsName = clazz.getName() + RFType.SERVICE_REQUEST.getPostFix();
		writer.println("public interface " + targetClsName + " extends RequestContext {");
		writer.println();
		JavaMethod[] methods = clazz.getMethods();
		for (JavaMethod method : methods) {
			for (Annotation flAn : method.getAnnotations()) {
				if (flAn.getType().getJavaClass().isA(MARKER_RF_SERVICE_METHOD)) {
					writer.println();
					if (method.isStatic()) {
						writer.print("	Request<" + getMappedType(method.getReturnType(), builder) + "> ");
					} else {
						throw new MojoExecutionException("Instance Request are not supported for @RFServiceMethod apply it only to static methods ");
					}
					writer.print(" " + method.getName() + "(");
					JavaParameter parameters[] = method.getParameters();
					if (parameters != null && parameters.length > 0) {
						boolean first = true;
						for (JavaParameter param : parameters) {
							if (first) {
								first = false;
							} else {
								writer.print(", ");
							}
							writer.print(getMappedType(param.getType(), builder) + " " + param.getName());
						}
					}
					writer.println(");");
				}
			}
		}
		writer.println("}");
		writer.close();
	}

	/**
	 * @param clazz
	 *            POJO or bean class from which Entity Request interface will be generated
	 * @param targetFile
	 *            Entity Request Interface which will be generated
	 * @throws Exception
	 *             generation failure
	 */
	private void generateEntityRequest(JavaClass clazz, File targetFile, JavaDocBuilder builder) throws Exception {
		PrintWriter writer = new PrintWriter(targetFile, encoding);
		if (targetServicePackage != null && !("".equals(targetServicePackage))) {
			writer.println("package " + targetServicePackage + ";");
			writer.println();
		} else {
			throw new MojoExecutionException("<targetServicePackage> configuration is missing.");
		}
		writer.println("import " + IMP_RF_INSTANCE_REQUEST + ";");
		writer.println("import " + IMP_RF_REQUEST + ";");
		writer.println("import " + IMP_RF_REQUEST_CONTEXT + ";");
		writer.println("import " + IMP_RF_SERVICE + ";");
		writer.println();
		writer.println(" /* Generated type. dont change the contents */");
		writer.println();
		/*
		 * TODO: Generate javadoc comments which will tell end developer not to edit the file as it generated file.
		 */
		writer.println();
		writer.println("@Service(" + clazz.getFullyQualifiedName() + ".class)");
		String targetClsName = clazz.getName() + RFType.ENTITY_REQUEST.getPostFix();
		writer.println("public interface " + targetClsName + " extends RequestContext {");
		writer.println();
		JavaMethod[] methods = clazz.getMethods();
		for (JavaMethod method : methods) {
			for (Annotation flAn : method.getAnnotations()) {
				if (flAn.getType().getJavaClass().isA(MARKER_RF_SERVICE_METHOD)) {
					writer.println();
					if (method.isStatic()) {
						writer.print("	Request<" + getMappedType(method.getReturnType(), builder) + "> ");
					} else {
						writer.print("	InstanceRequest<" + getMappedType(clazz.asType(), builder) + ", " + getMappedType(method.getReturnType(), builder) + "> ");
					}
					writer.print(" " + method.getName() + "(");
					JavaParameter parameters[] = method.getParameters();
					if (parameters != null && parameters.length > 0) {
						boolean first = true;
						for (JavaParameter param : parameters) {
							if (first) {
								first = false;
							} else {
								writer.print(", ");
							}
							writer.print(getMappedType(param.getType(), builder) + " " + param.getName());
						}
					}
					writer.println(");");
				}
			}
		}
		writer.println("}");
		writer.close();
	}

	/**
	 * @param clazz
	 *            POJO or bean class from which Value Proxy interface will be generated
	 * @param targetFile
	 *            Value Proxy Interface which will be generated
	 * @throws Exception
	 *             generation failure
	 */
	private void generateValueProxy(JavaClass clazz, File targetFile, JavaDocBuilder builder) throws Exception {
		PrintWriter writer = new PrintWriter(targetFile, encoding);
		if (targetProxyPackage != null && !("".equals(targetProxyPackage))) {
			writer.println("package " + targetProxyPackage + ";");
			writer.println();
		} else {
			throw new MojoExecutionException("<targetProxyPackage> configuration is missing.");
		}
		writer.println("import " + IMP_RF_VALUE_PROXY + ";");
		writer.println("import " + IMP_RF_PROXY_FOR + ";");
		writer.println();
		writer.println(" /* Generated type dont change the contents */");
		writer.println();
		/*
		 * TODO: Generate javadoc comments which will tell end developer not to edit the file as it generated file.
		 */
		writer.println();
		writer.println("@ProxyFor(" + clazz.getFullyQualifiedName() + ".class)");
		writer.println("public interface " + clazz.getName() + RFType.VALUE_PROXY.getPostFix() + " extends ValueProxy {");
		writer.println();
		JavaMethod[] methods = clazz.getMethods();
		for (JavaMethod method : methods) {
			for (Annotation flAn : method.getAnnotations()) {
				if (flAn.getType().getJavaClass().isA(MARKER_RF_PROXY_METHOD)) {
					writer.println();
					writer.print("	" + getMappedType(method.getReturnType(), builder) + " ");
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
							writer.print(" " + getMappedType(param.getType(), builder) + " " + param.getName());
						}
					}
					writer.println(");");
				}
			}
		}

		writer.println("}");
		writer.close();
	}

	/**
	 * @param clazz
	 *            POJO or bean class from which Entity Proxy interface will be generated
	 * @param targetFile
	 *            Entity Proxy Interface which will be generated
	 * @throws Exception
	 *             generation failure
	 */
	private void generateEntityProxy(JavaClass clazz, File targetFile, JavaDocBuilder builder) throws Exception {
		PrintWriter writer = new PrintWriter(targetFile, encoding);
		if (targetProxyPackage != null && !("".equals(targetProxyPackage))) {
			writer.println("package " + targetProxyPackage + ";");
			writer.println();
		} else {
			throw new MojoExecutionException("<targetProxyPackage> configuration is missing.");
		}
		writer.println("import " + IMP_RF_ENTITY_PROXY + ";");
		writer.println("import " + IMP_RF_ENTITY_PROXY_ID + ";");
		writer.println("import " + IMP_RF_PROXY_FOR + ";");
		writer.println();
		writer.println(" /* Generated type dont change the contents */");
		writer.println();
		/*
		 * TODO: Generate javadoc comments which will tell end developer not to edit the file as it generated file.
		 */
		writer.println();
		writer.println("@ProxyFor(" + clazz.getFullyQualifiedName() + ".class)");
		String targetClsName = clazz.getName() + RFType.ENTITY_PROXY.getPostFix();
		writer.println("public interface " + targetClsName + " extends EntityProxy {");
		writer.println();
		JavaMethod[] methods = clazz.getMethods();
		for (JavaMethod method : methods) {
			for (Annotation flAn : method.getAnnotations()) {
				if (flAn.getType().getJavaClass().isA(MARKER_RF_PROXY_METHOD)) {
					writer.println();
					writer.print("	" + getMappedType(method.getReturnType(true), builder) + " ");
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
							writer.print(" " + getMappedType(param.getType(), builder) + " " + param.getName());
						}
					}
					writer.println(");");
				}
			}
		}
		if (generateStableId) {
			writer.println("	EntityProxyId<" + targetClsName + "> stableId();");
		}
		writer.println("}");
		writer.close();
	}

	private String getMappedType(Type type, JavaDocBuilder builder) throws Exception {
		if (checkIfGenericType(type)) {
			String[] genericParams = getInnerTypes(type.getGenericValue());
			StringBuilder finalCls = new StringBuilder();
			finalCls.append(getOuterType(type.getGenericValue()));
			finalCls.append("<");
			for (int index = 0; index < genericParams.length; index++) {
				if (index > 0) {
					finalCls.append(", ");
				}
				String genericParam = genericParams[index];
				JavaClass jvCls = getJavaClass(genericParam, builder);
				finalCls.append(getMappedType(jvCls.asType(), builder));
			}
			finalCls.append(">");
			return finalCls.toString();
		} else {
			return getFinalTypeString(type);
		}
	}

	private String getOuterType(String fqdnName) {
		return fqdnName.substring(0, fqdnName.indexOf('<'));
	}

	private String[] getInnerTypes(String fqdnName) {
		String inside = fqdnName.substring(fqdnName.indexOf('<') + 1, fqdnName.lastIndexOf('>'));
		System.out.println("Iner class => " + inside);
		return inside.split(",");
	}

	private String getFinalTypeString(Type type) throws Exception {
		JavaClass jvCls = type.getJavaClass();
		if (wrappers.containsKey(jvCls.getName())) {
			return wrappers.get(jvCls.getName());
		}
		for (Annotation an : jvCls.getAnnotations()) {
			if (an.getType().getJavaClass().isA(MARKER_RF_VALUE_PROXY)) {
				return targetProxyPackage + "." + jvCls.getName() + RFType.VALUE_PROXY.getPostFix();
			} else if (an.getType().getJavaClass().isA(MARKER_RF_ENTITY_PROXY)) {
				return targetProxyPackage + "." + jvCls.getName() + RFType.ENTITY_PROXY.getPostFix();
			}
		}
		if (jvCls.asType().getValue().startsWith("java.lang")) {
			return jvCls.getName();
		}

		/*
		 * TODO: Need to add supported data type check if there is any other custom data type which is not either entity
		 * or value object. Then we need to throw exception
		 */
		return jvCls.asType().getValue();
	}

	private JavaClass getJavaClass(String className, JavaDocBuilder builder) {
		return builder.getClassByName(className);
	}

	private boolean checkIfGenericType(Type type) {
		String clsName = type.getGenericValue();
		return clsName != null && clsName.contains("<") && clsName.contains(">");
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

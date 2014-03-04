package com.agnie.tools.war;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import com.agnie.common.tools.CommandProcessor;
import com.beust.jcommander.Parameter;

/**
 * @author Pandurang Patil 02-Mar-2014
 * 
 */
public class RepackProcessor extends CommandProcessor {

	@Parameter(names = { "-s", "--source" }, description = "Source directory location where input wars files are kept", required = true)
	private String				source;

	@Parameter(names = { "-v", "--verbose" }, description = "Verbose output")
	private Boolean				verbose				= false;

	private static final String	sharedLocateion		= "shared";
	private static final String	outPutLocation		= "repackaged";
	private Set<String>			commonLibs			= new HashSet<String>();
	private Set<String>			allLibs				= new HashSet<String>();
	private Set<String>			commonCopiedLibs	= new HashSet<String>();

	@Override
	public void process() throws Exception {
		File f = new File(source);
		if (f.isDirectory()) {
			File[] files = f.listFiles(new FileFilter() {

				public boolean accept(File pathname) {
					if (pathname.isFile() && pathname.getName().endsWith(".war")) {
						return true;
					}
					return false;
				}
			});
			if (files == null || files.length < 2) {
				System.out.println("You need to have at least have two war files in your source directory");
				return;
			}
			System.out.println("Scanning existing war files...");
			for (File file : files) {
				scan(file);
			}
			System.out.println("Scan complete...");

			if (commonLibs.size() == 0) {
				System.out.println("There are no common libraries found, so there is no need to repackage the wars, you can use them as is");
			} else {
				java.io.File mainDir = new java.io.File(source + java.io.File.separator + sharedLocateion);
				mainDir.mkdir();
				mainDir = new java.io.File(source + java.io.File.separator + outPutLocation);
				mainDir.mkdir();
				System.out.println("Repackaging...");
				for (File file : files) {
					repackage(file);
				}
			}
		} else {
			System.out.println("You need to specify directory which contains all war files that needs to be repackaged.");
		}
	}

	/**
	 * Repackage war files by excluding common jar files.
	 * 
	 * @param warFile
	 * @throws IOException
	 */
	public void repackage(File warFile) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(source + java.io.File.separator + outPutLocation + java.io.File.separator + warFile.getName()));
		System.out.print("\nRepackaging war =>" + warFile.getName());
		JarOutputStream target = new JarOutputStream(fos);
		JarFile war = new JarFile(warFile);
		Enumeration<JarEntry> files = war.entries();
		while (files.hasMoreElements()) {
			java.util.jar.JarEntry file = files.nextElement();
			extractAndAdd(war, file, target);
		}
		System.out.println();
		target.close();
		war.close();
	}

	/**
	 * Scan war file contents to identify shared libraries.
	 * 
	 * @param warFile
	 * @throws Exception
	 */
	public void scan(File warFile) throws Exception {
		JarFile war = new JarFile(warFile);
		Enumeration<JarEntry> files = war.entries();
		while (files.hasMoreElements()) {
			JarEntry file = files.nextElement();
			String fileName = file.getName();
			if (!file.isDirectory() && fileName.startsWith("WEB-INF/lib/") && fileName.endsWith(".jar")) {
				// file is jar file.
				if (!commonLibs.contains(fileName)) {
					if (allLibs.contains(fileName)) {
						// If given file is there in all libs that means it been added while scanning previous war file.
						// That means its common file between two wars.
						commonLibs.add(fileName);
					} else {
						// other wise add it in all libs list.
						allLibs.add(fileName);
					}
				}
			}
		}
		war.close();
	}

	/**
	 * Extract given file from source war file and add it to destination war file. While doing that it will exclude
	 * common jar files and copy to shared folder.
	 * 
	 * @param sourceWarFile
	 *            Source war file.
	 * @param source
	 *            source file from source war file.
	 * @param target
	 *            Target war file output stream.
	 * @throws IOException
	 */
	private void extractAndAdd(JarFile sourceWarFile, JarEntry source, JarOutputStream target) throws IOException {
		String fileName = source.getName();
		if (!source.isDirectory() && commonLibs.contains(fileName)) {
			if (commonCopiedLibs.contains(fileName)) {
				return;
			} else {
				if (verbose)
					System.out.println("copying to shared Lib => " + fileName);
				else
					System.out.print(" .");
				java.io.File newFile = new java.io.File(this.source + java.io.File.separator + sharedLocateion + java.io.File.separator
						+ fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length()));
				java.io.InputStream is = sourceWarFile.getInputStream(source); // get the input stream
				java.io.FileOutputStream fos = new java.io.FileOutputStream(newFile);
				while (is.available() > 0) { // write contents of 'is' to 'fos'
					fos.write(is.read());
				}
				fos.close();
				is.close();
				commonCopiedLibs.add(fileName);
			}
		} else {
			if (verbose)
				System.out.println("adding to war =>" + fileName);
			else
				System.out.print(" .");
			target.putNextEntry(source);
			if (!source.isDirectory()) {

				java.io.InputStream is = sourceWarFile.getInputStream(source); // get the input stream
				while (is.available() > 0) { // write contents of 'is' to 'target war'
					target.write(is.read());
				}
			}
			target.closeEntry();
		}
	}

	public static void main(String[] args) throws Exception {
		RepackProcessor rp = new RepackProcessor();
		rp.source = "/Users/pandurang/projects/billing/code/billing/service/target";
		rp.process();
	}
}

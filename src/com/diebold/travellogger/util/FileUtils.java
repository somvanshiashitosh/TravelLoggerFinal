package com.diebold.travellogger.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

public class FileUtils {

	private static String getFileType(String type) {
		String[] f = type.split("/");
		if (f.length == 2) {
			return f[1];
		}
		return null;
	}

	public static String getFilenameWithOracleId(String type, Integer name) throws Exception {
		type = getFileType(type);
		String filename = null;
		if (type != null) {
			if (name != null) {
				filename = name.toString() + "." + type;
			} else {
				throw new Exception("Please Enter OracleId");
			}
		}
		return filename;
	}

	public static void clearFilesWithSameName(String filepath, String type, final String filename) {
		// TODO Auto-generated method stub
		File dir = new File(filepath);
		type = getFileType(type);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith(filename);
			}
		};
		String children[] = dir.list(filter);
		for (int i = 0; i < children.length; i++) {
			File file = new File(filepath, children[i]);
			file.delete();
		}
	}

	public static boolean saveFile(String filename, String filepath, UploadedFile file) {
		if (null != file) {
			InputStream input = null;
			OutputStream output = null;
			try {
				input = file.getInputstream();
				output = new FileOutputStream(new File(filepath, filename));
				IOUtils.copy(input, output);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			} finally {
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(output);
			}
			return true;
		}
		return false;
	}

	public static String getFilePath(String attribute) throws Exception {
		Properties properties = new Properties();
		InputStream inputPath = new FileInputStream(
				"C:\\Users\\somvaa\\workspace\\Travel Logger\\WebContent\\resources\\passportFileLocation.properties");
		properties.load(inputPath);
		return properties.getProperty(attribute);
	}

}

package com.diebold.travellogger.beans;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class testing {
	private Integer oracleId;
	private Part passportPdf;
	private String added;
	
	private UploadedFile uploadedFile; 

	public void upload() {
	    String fileName = uploadedFile.getFileName();
	    System.out.println(fileName);
	    String contentType = uploadedFile.getContentType();
	    System.out.println(contentType);
	    
	    String filePath="C:\\Users\\somvaa\\Desktop\\passportPDF\\";
        byte[] bytes=null;
 
            if (null!=this.getUploadedFile()) {
                bytes = uploadedFile.getContents();
                File file=new File(filePath+fileName);
                BufferedOutputStream stream;
				try {
					stream = new BufferedOutputStream(new FileOutputStream(file));
					   stream.write(bytes);
		                stream.close();
		                this.setAdded("uploaded");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
             
            }
	 
	}
	
	public String uploadID() {
		System.out.println(oracleId);
		System.out.println("in uploadID");
		return "success";
	}
	
	public void success()
	{
		System.out.println(oracleId);
		System.out.println("in success");
	}
	
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	public Integer getOracleId() {
		return oracleId;
	}

	public void setOracleId(Integer oracleId) {
		this.oracleId = oracleId;
	}

	public Part getPassportPdf() {
		return passportPdf;
	}

	public void setPassportPdf(Part passportPdf) {
		this.passportPdf = passportPdf;
	}

	
	public String getAdded() {
		return added;
	}

	public void setAdded(String added) {
		this.added = added;
	}

	private static String getFileName(Part file)
	{
		for(String name: file.getHeader("content-disposition").split(";"))
		{
			if(name.trim().startsWith("filename"))
			{
				String filename= name.substring(name.indexOf("=")+1).trim().replace("\"", "");
				System.out.println(filename);
				return filename.substring(filename.lastIndexOf("/")+1).substring(filename.lastIndexOf("\\")+1);
			}
		}
		return null;
	}
	
	public void savePassportPdf()
	{
		System.out.println("in pdf saving method");
		try {
			passportPdf.write("C:\\Users\\somvaa\\Desktop\\passportPDF\\"+getFileName(passportPdf));
			System.out.println(this.getOracleId());
			this.setAdded("uploaded");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

}

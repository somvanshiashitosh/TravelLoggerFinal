package com.diebold.travellogger.pojo;

import java.io.InputStream;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class fileDownload {
    private StreamedContent file;
    
    public fileDownload(String link,Integer OracleId) { 
    	System.out.println("file constructor");
    	try
    	{
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:\\Users\\somvaa\\Desktop\\passportPDF\\101010.pdf");
        file = new DefaultStreamedContent(stream, "pdf", OracleId+".jpg");
        System.out.println(file.getContentEncoding());
        System.out.println(file.getContentType());
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    }
    public StreamedContent getFile() {
    	System.out.println(file.getName());
        return file;
    }
	@Override
	public String toString() {
		return "fileDownload [file=" + file + "]";
	}
    
}

package com.diebold.travellogger.pojo;

public class Mail {
	private String dailogHeader;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String body;
	public String getDailogHeader() {
		return dailogHeader;
	}
	public void setDailogHeader(String dailogHeader) {
		this.dailogHeader = dailogHeader;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "Mail [dailogHeader=" + dailogHeader + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", subject="
				+ subject + ", body=" + body + "]";
	}
	
	
}

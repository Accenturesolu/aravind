package com.accenture.sts.message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Messages{

	
	private String invalidcredentials;
	private String resourcenotactive;
	private String passwordchange;
	private String passwordnotmatch;
	
	private String resourceinsert;
	private String employeeidexist;
	private String modifyresource;
	
	private String mandatory;
	private String verifyticketid;
	private String clarificationupdate;
	private String clarificationresponse;
	private String clarificationdelete;
	
	private String successadd;
	private String failadd;
	private String successdelete;
	private String errorupdate;
	private String successassign;
	private String successreassign;
	private String selectemployee;
	
	private String uploadsuccess;
	private String uploadfail;
	
	InputStream  inputStream;
	private Properties getPropValues() throws IOException,FileNotFoundException {
		Properties prop = new Properties();
		try { 
		
			inputStream = getClass().getResourceAsStream("message.properties");
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop;
	}
	public String getInvalidcredentials() throws IOException {
		this.invalidcredentials = getPropValues().getProperty("INVALIDCREDENTIALS");
		return invalidcredentials;
	}
	public String getResourcenotactive() throws IOException {
		this.resourcenotactive = getPropValues().getProperty("RESOURCENOTACTIVE");
		return resourcenotactive;
	}
	public String getPasswordchange() throws IOException {
		this.passwordchange = getPropValues().getProperty("PASSWORDCHANGE");
		return passwordchange;
	}
	public String getPasswordnotmatch() throws IOException  {
		this.passwordnotmatch = getPropValues().getProperty("PASSWORDNOTMATCH");
		return passwordnotmatch;
	}
	
	public String getResourceinsert() throws IOException{
		this.resourceinsert = getPropValues().getProperty("RESOURCEINSERT");
		return resourceinsert;
	}
	public String getEmployeeidexist() throws IOException {
		this.employeeidexist = getPropValues().getProperty("EMPLOYEEIDEXIST");
		return employeeidexist;
	}
	public String getModifyresource() throws IOException{
		this.modifyresource = getPropValues().getProperty("MODIFYRESOURCE");
		return modifyresource;
	}
	public String getMandatory() throws IOException{
		this.mandatory = getPropValues().getProperty("MANDATORY");
		return mandatory;
	}
	public String getVerifyticketid() throws IOException{
		this.verifyticketid = getPropValues().getProperty("VERIFYTICKETID");
		return verifyticketid;
	}
	public String getClarificationupdate() throws IOException{		
		this.clarificationupdate = getPropValues().getProperty("CLARIFICATIONUPDATE");
		return clarificationupdate;
	}
	public String getClarificationresponse() throws IOException{
		this.clarificationresponse = getPropValues().getProperty("CLARIIFCATIONRESPONSE");
		return clarificationresponse;
	}
	public String getClarificationdelete() throws IOException{
		this.clarificationdelete = getPropValues().getProperty("CLARIFICATIONDELETE");
		return clarificationdelete;
	}
	public String getSuccessadd() throws IOException {
		this.successadd = getPropValues().getProperty("SUCCESSADD");
		return successadd;
	}
	public String getFailadd() throws IOException {
		this.failadd = getPropValues().getProperty("FAILADD");
		return failadd;
	}
	
	public String getSuccessdelete() throws IOException{
		this.successdelete = getPropValues().getProperty("SUCCESSDELETE");
		return successdelete;
	}
	public String getErrorupdate() throws IOException{
		this.errorupdate = getPropValues().getProperty("ERRORUPDATE");
		return errorupdate;
	}
	public String getSuccessassign() throws IOException{
		this.successassign = getPropValues().getProperty("SUCCESSASSIGN");
		return successassign;
	}
	public String getSuccessreassign() throws IOException{
		this.successreassign = getPropValues().getProperty("SUCCESSREASSIGN");
		return successreassign;
	}
	public String getSelectemployee() throws IOException{
		this.selectemployee = getPropValues().getProperty("SELECTEMPLOYEE");
		return selectemployee;
	}
	public String getUploadsuccess() throws IOException{
		this.uploadsuccess = getPropValues().getProperty("UPLOADSUCCESS");
		return uploadsuccess;
	}
	public String getUploadfail() throws IOException{
		this.uploadfail = getPropValues().getProperty("UPLOADFAIL");
		return uploadfail;
	}
	
	
	
}

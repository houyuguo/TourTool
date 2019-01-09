package com.njusw.tourtool.bean;

public class RResult {

	private String success;
	private String errMsg;
	private String user;
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		if(errMsg == null)
			this.errMsg = null;
		else
		    this.errMsg = errMsg;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	

	
	
	
	
	
}

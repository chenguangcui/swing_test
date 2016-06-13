package com.ccg.test;

public class ShortUrlVo {

	private String tinyurl;
	private Integer status;
	private String longurl;
	private String err_msg;
	public String getTinyurl() {
		return tinyurl;
	}
	public void setTinyurl(String tinyurl) {
		this.tinyurl = tinyurl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getLongurl() {
		return longurl;
	}
	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	
}

package com.max.clodop.entity;

import java.io.Serializable;

public class PrintResult implements Serializable {
	private boolean success;
	private String tid;

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
}

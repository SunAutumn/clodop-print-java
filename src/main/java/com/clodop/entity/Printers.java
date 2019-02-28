package com.clodop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.Map;

public class Printers implements Serializable {

	@JSONField(name = "default")
	private String defaultPrinter;

	@JSONField(name = "list")
	private Map<Integer, Printer> printers;

	public String getDefaultPrinter() {
		return this.defaultPrinter;
	}

	public void setDefaultPrinter(String defaultPrinter) {
		this.defaultPrinter = defaultPrinter;
	}

	public Map<Integer, Printer> getPrinters() {
		return this.printers;
	}

	public void setPrinters(Map<Integer, Printer> printers) {
		this.printers = printers;
	}
}

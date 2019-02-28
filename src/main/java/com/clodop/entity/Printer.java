package com.clodop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.Map;

public class Printer implements Serializable {
	private String name;

	@JSONField(name = "DriverName")
	private String driverName;

	@JSONField(name = "PortName")
	private String portName;

	@JSONField(name = "Orientation")
	private String orientation;

	@JSONField(name = "PaperSize")
	private String paperSize;

	@JSONField(name = "PaperLength")
	private String paperLength;

	@JSONField(name = "PaperWidth")
	private String paperWidth;

	@JSONField(name = "Copies")
	private String copies;

	@JSONField(name = "DefaultSource")
	private String defaultSource;

	@JSONField(name = "DriPrintQualityverName")
	private String PrintQuality;

	@JSONField(name = "Color")
	private String color;

	@JSONField(name = "Duplex")
	private String duplex;

	@JSONField(name = "FormName")
	private String formName;

	@JSONField(name = "Comment")
	private String comment;

	@JSONField(name = "DriverVersion")
	private String driverVersion;

	@JSONField(name = "DCOrientation")
	private String dcOrientation;

	@JSONField(name = "MaxExtentWidth")
	private String maxExtentWidth;

	@JSONField(name = "MaxExtentLength")
	private String maxExtentLength;

	@JSONField(name = "MinExtentWidth")
	private String minExtentWidth;

	@JSONField(name = "MinExtentlength")
	private String minExtentlength;

	@JSONField(name = "pagelist")
	private Map<Integer, PrinterPage> pageList;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDriverName() {
		return this.driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getPortName() {
		return this.portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getOrientation() {
		return this.orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getPaperSize() {
		return this.paperSize;
	}

	public void setPaperSize(String paperSize) {
		this.paperSize = paperSize;
	}

	public String getPaperLength() {
		return this.paperLength;
	}

	public void setPaperLength(String paperLength) {
		this.paperLength = paperLength;
	}

	public String getPaperWidth() {
		return this.paperWidth;
	}

	public void setPaperWidth(String paperWidth) {
		this.paperWidth = paperWidth;
	}

	public String getCopies() {
		return this.copies;
	}

	public void setCopies(String copies) {
		this.copies = copies;
	}

	public String getDefaultSource() {
		return this.defaultSource;
	}

	public void setDefaultSource(String defaultSource) {
		this.defaultSource = defaultSource;
	}

	public String getPrintQuality() {
		return this.PrintQuality;
	}

	public void setPrintQuality(String printQuality) {
		this.PrintQuality = printQuality;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDuplex() {
		return this.duplex;
	}

	public void setDuplex(String duplex) {
		this.duplex = duplex;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDriverVersion() {
		return this.driverVersion;
	}

	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}

	public String getDcOrientation() {
		return this.dcOrientation;
	}

	public void setDcOrientation(String dcOrientation) {
		this.dcOrientation = dcOrientation;
	}

	public String getMaxExtentWidth() {
		return this.maxExtentWidth;
	}

	public void setMaxExtentWidth(String maxExtentWidth) {
		this.maxExtentWidth = maxExtentWidth;
	}

	public String getMaxExtentLength() {
		return this.maxExtentLength;
	}

	public void setMaxExtentLength(String maxExtentLength) {
		this.maxExtentLength = maxExtentLength;
	}

	public String getMinExtentWidth() {
		return this.minExtentWidth;
	}

	public void setMinExtentWidth(String minExtentWidth) {
		this.minExtentWidth = minExtentWidth;
	}

	public String getMinExtentlength() {
		return this.minExtentlength;
	}

	public void setMinExtentlength(String minExtentlength) {
		this.minExtentlength = minExtentlength;
	}

	public Map<Integer, PrinterPage> getPageList() {
		return this.pageList;
	}

	public void setPageList(Map<Integer, PrinterPage> pageList) {
		this.pageList = pageList;
	}
}

package com.max.clodop.entity;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class PageData extends HashMap<String, String> implements BaseEntity {
	public static final String TOP_KEY = "top";
	public static final String LEFT_KEY = "left";
	public static final String WIDTH_KEY = "width";
	public static final String HEIGHT_KEY = "height";
	public static final String PRINTTASK_KEY = "printtask";

	public String getTop() {
		return (String) get("top");
	}

	public void setTop(Integer top) {
		String topStr = top != null ? new StringBuilder().append("").append(top).toString() : "";
		put("top", topStr);
	}

	public String getLeft() {
		return (String) get("left");
	}

	public void setLeft(Integer left) {
		String leftStr = left != null ? new StringBuilder().append("").append(left).toString() : "";
		put("left", leftStr);
	}

	public String getWidth() {
		return (String) get("width");
	}

	public void setWidth(Integer width) {
		String widthStr = width != null ? new StringBuilder().append("").append(width).toString() : "";
		put("width", widthStr);
	}

	public String getHeight() {
		return (String) get("height");
	}

	public void setHeight(Integer height) {
		String heightStr = height != null ? new StringBuilder().append("").append(height).toString() : "";
		put("height", heightStr);
	}

	public String getPrinttask() {
		return (String) get("printtask");
	}

	public void setPrinttask(String printtask) {
		put("printtask", printtask);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		StringBuilder printModeNames = new StringBuilder();
		for (Map.Entry entry : entrySet()) {
			sb.append((String) entry.getKey()).append("=").append((String) entry.getValue()).append(DELIM_CHAR);
			if ((!"top".equals(entry.getKey())) && (!"left".equals(entry.getKey())) && (!"width".equals(entry.getKey()))
					&& (!"height".equals(entry.getKey())) && (!"printtask".equals(entry.getKey()))
					&& (!"printerindex".equals(entry.getKey())) && (!"printerindexa".equals(entry.getKey()))
					&& (!"printersubid".equals(entry.getKey())) && (!"orient".equals(entry.getKey()))
					&& (!"pagewidth".equals(entry.getKey())) && (!"pageheight".equals(entry.getKey()))
					&& (!"pagename".equals(entry.getKey())) && (!"printcopies".equals(entry.getKey()))
					&& (!"setup_bkimg".equals(entry.getKey()))) {
				printModeNames.append(";").append((String) entry.getKey());
			}
		}
		if (StringUtils.isNotEmpty(printModeNames)) {
			sb.append("printmodenames=").append(printModeNames).append(DELIM_CHAR);
		}
		return sb.toString();
	}
}

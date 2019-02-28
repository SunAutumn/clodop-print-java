package com.clodop.entity;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class PrintStyle extends HashMap<String, String> implements BaseEntity {
	public String toString() {
		StringBuilder strData = new StringBuilder();
		StringBuilder styleClassNames = new StringBuilder();
		for (Map.Entry entry : entrySet()) {
			strData.append((String) entry.getKey()).append("=").append((String) entry.getValue()).append(DELIM_CHAR);
			styleClassNames.append(";").append((String) entry.getKey());
		}
		if (StringUtils.isNotEmpty(styleClassNames)) {
			strData.append("printstyleclassnames=").append(styleClassNames).append(DELIM_CHAR);
		}
		return strData.toString();
	}
}

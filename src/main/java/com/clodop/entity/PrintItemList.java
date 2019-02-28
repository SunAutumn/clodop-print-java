package com.clodop.entity;


import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class PrintItemList extends ArrayList<Map<String, String>> implements BaseEntity {
	public String toString() {
		StringBuilder strData = new StringBuilder();
		strData.append("itemcount=").append(size()).append(DELIM_CHAR);
		int itemNO;
		for (int i = 0; i < size(); i++) {
			itemNO = i + 1;
			Map<String, String> item = get(i);
			StringBuilder itemStyless = new StringBuilder();
			for (String key : item.keySet()) {
				if ((!"beginpage".equals(key)) && (!"beginpagea".equals(key)) && (!"type".equals(key))
						&& (!"top".equals(key)) && (!"left".equals(key)) && (!"width".equals(key))
						&& (!"height".equals(key)))
					itemStyless.append(";").append(key);
			}
			strData.append(itemNO).append("_itemstylenames=").append(itemStyless).append(DELIM_CHAR);
			for (Entry entry : item.entrySet()) {
				strData.append(itemNO).append("_").append((String) entry.getKey()).append("=").append(entry.getValue())
						.append(DELIM_CHAR);
			}
		}

		return strData.toString();
	}
}

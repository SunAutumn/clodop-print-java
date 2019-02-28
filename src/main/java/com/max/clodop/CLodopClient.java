package com.max.clodop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.max.clodop.entity.*;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.body.MultipartBody;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class CLodopClient {

	private String cLodopServer;
	private String cLodopClientId;
	private PageData pageData = new PageData();

	private PrintItemList itemDatas = new PrintItemList();

	private Map<String, String> printStyle = new HashMap();
	private PrintStyle itemCNameStyles = new PrintStyle();
	private ScriptObjectMirror clodop;
	private int printTaskIndex = 0;

	public CLodopClient(String cLodopServer) {
		this.cLodopServer = cLodopServer;
		init();
		this.cLodopClientId = ((String) this.clodop.get("strWebPageID"));
	}

	public Printers getPrinter() {
		ScriptObjectMirror mirror = (ScriptObjectMirror) this.clodop.get("Printers");
		String printersJson = JSON.toJSONString(mirror);
		return (Printers) JSON.parseObject(printersJson, Printers.class);
	}

	public void printInit(String printTaskName) {
		printInita(null, null, null, null, printTaskName);
	}

	public void printInita(Integer top, Integer left, Integer width, Integer height, String printTaskName) {
		this.pageData.clear();
		this.itemDatas.clear();
		this.printStyle.clear();
		this.itemCNameStyles.clear();

		this.pageData.setTop(top);
		this.pageData.setLeft(left);
		this.pageData.setWidth(width);
		this.pageData.setHeight(height);
		this.pageData.setPrinttask(printTaskName);
	}

	public void setPrintPagesize(Integer intOrient, String pageWidth, String pageHeight, String pageName) {
		if (intOrient != null)
			this.pageData.put("orient", intOrient + "");
		if (StringUtils.isEmpty(pageWidth))
			this.pageData.put("pagewidth", pageWidth);
		if (StringUtils.isEmpty(pageHeight))
			this.pageData.put("pageheight", pageHeight);
		if (StringUtils.isEmpty(pageName))
			this.pageData.put("pagename", pageName);
	}

	public void setPrintMode(String modeType, String modeValue) {
		if ((StringUtils.isEmpty(modeType)) || (StringUtils.isEmpty(modeValue))) {
			return;
		}

		modeType = modeType.toLowerCase();
		this.pageData.put(modeType, modeValue);
	}

	public void setPrintStyle(String styleName, String styleValue) {
		if ((StringUtils.isEmpty(styleName)) || (StringUtils.isEmpty(styleValue))) {
			return;
		}

		styleName = styleName.toLowerCase();
		this.printStyle.put(styleName, styleValue);
	}

	public void setPrintCopies(Integer copies) {
		if (copies != null)
			this.pageData.put("printcopies", String.valueOf(copies));
	}

	public void setPrintStylea(Integer itemNo, String strKey, String styleValue) {
		String itemNoStr = itemNo + "";
		if (StringUtils.isEmpty(strKey))
			strKey = "";
		if (StringUtils.isEmpty(styleValue))
			styleValue = "";
		if ((StringUtils.isEmpty(itemNoStr)) || (StringUtils.isEmpty(strKey))) {
			return;
		}
		if (this.itemDatas.isEmpty()) {
			String value = (String) this.pageData.get("add_print_program_data");
			if (StringUtils.isNotEmpty(value)) {
				this.itemCNameStyles.put(strKey.toLowerCase() + "-" + itemNo, styleValue);
			}
			return;
		}

		strKey = strKey.toLowerCase();
		if ("type".equals(strKey)) {
			return;
		}

		if ((itemNo.intValue() < 0) || (itemNo.intValue() >= this.itemDatas.size())) {
			return;
		}

		this.itemDatas.get(itemNo.intValue()).put(strKey, styleValue);
	}

	public void setPrinterIndex(String index, String keyModeName) {
		if (StringUtils.isEmpty(keyModeName)) {
			keyModeName = "printerindex";
		}

		this.pageData.put(keyModeName, index);
	}

	public void setPrinterIndexa(String index) {
		setPrinterIndex(index, "printerindexa");
	}

	public boolean addPrintText(String top, String left, String width, String height, String text) {
		return addItemArray(Integer.valueOf(2), top, left, width, height, text, null, null, null, null, null, null,
				null, null);
	}

	public boolean addPrintTexta(String itemName, String top, String left, String width, String height, String text) {
		return addItemArray(Integer.valueOf(2), top, left, width, height, text, itemName, null, null, null, null, null,
				null, null);
	}

	public boolean addPrintHtm(String top, String left, String width, String height, String html) {
		return addItemArray(Integer.valueOf(4), top, left, width, height, html, null, null, null, null, null, null,
				null, null);
	}

	public boolean addPrintHtml(String top, String left, String width, String height, String html) {
		return addItemArray(Integer.valueOf(1), top, left, width, height, html, null, null, null, null, null, null,
				null, null);
	}

	public boolean addPrintHtmla(String itemName, String top, String left, String width, String height, String html) {
		return addItemArray(Integer.valueOf(4), top, left, width, height, html, itemName, null, null, null, null, null,
				null, null);
	}

	public boolean addPrintBarcode(String top, String left, String width, String height, String barType,
			String barValue) {
		return addItemArray(Integer.valueOf(9), top, left, width, height, barValue, null, null, null, null, null, null,
				barType, null);
	}

	public boolean addPrintBarcode(String itemName, String top, String left, String width, String height,
			String barType, String barValue) {
		return addItemArray(Integer.valueOf(9), top, left, width, height, barValue, itemName, null, null, null, null,
				null, barType, null);
	}

	private boolean addItemArray(Integer type, String top, String left, String width, String height, String strContent,
			String itemName, Integer shapeType, Integer intPenStyle, Integer intPenWidth, Integer intColor,
			String isLinePosition, String barType, String strChartTypess) {
		if ((StringUtils.isEmpty(top)) || (StringUtils.isEmpty(left)) || (StringUtils.isEmpty(width))
				|| (StringUtils.isEmpty(height)) || (StringUtils.isEmpty(strContent))) {
			return false;
		}
		Map oneItem = new HashMap();
		for (Map.Entry entry : this.printStyle.entrySet()) {
			oneItem.put(entry.getKey(), entry.getValue());
		}
		oneItem.put("type", String.valueOf(type));
		oneItem.put("top", top);
		oneItem.put("left", left);
		oneItem.put("width", width);
		oneItem.put("height", height);
		if (StringUtils.isNotEmpty(strContent)) {
			oneItem.put("content", strContent);
		}

		if (StringUtils.isNotEmpty(itemName)) {
			oneItem.put("itemname", itemName);
		}

		if (StringUtils.isNotEmpty(barType)) {
			oneItem.put("fontname", barType);
		}

		this.itemDatas.add(oneItem);
		return true;
	}

	public PrintResult print() {
		String taskId = genTaskId();
		PrintResult result = new PrintResult();
		result.setTid(taskId);
		try {
			MultipartBody post = Unirest.post(this.cLodopServer + "/c_dopostdatas").field("charset", "丂")
					.field("tid", taskId).field("post", createPostData());
			HttpResponse response = post.asString();
			if (response.getStatus() == 204)
				result.setSuccess(true);
		}
		catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}

	public boolean isPrintSuccess(PrintResult result) {
		boolean lastResult = false;
		try {
			if (result.isSuccess()) {
				HttpRequest request = Unirest.get(this.cLodopServer + "/c_lastresult.js")
						.queryString("times", Long.valueOf(System.currentTimeMillis()))
						.queryString("tid", result.getTid());
				HttpResponse response = request.asString();
				if ((response.getStatus() == 200) && (StringUtils.isNotEmpty((CharSequence) response.getBody()))) {
					ScriptEngineManager manager = new ScriptEngineManager();
					ScriptEngine engine = manager.getEngineByName("javascript");
					engine.eval((String) response.getBody());
					lastResult = Boolean.valueOf((String) engine.get("CLodop_ACTLastResult")).booleanValue();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return lastResult;
	}

	private String genTaskId() {
		SimpleDateFormat df = new SimpleDateFormat("HHmmss");
		this.printTaskIndex += 1;
		String taskId = this.cLodopClientId + df.format(new Date()) + "_" + this.printTaskIndex;
		return taskId;
	}

	private String createPostData() {
		StringBuilder strData = new StringBuilder("act=print" + BaseEntity.DELIM_CHAR);

		strData.append(this.pageData.toString());
		strData.append(this.itemCNameStyles.toString());
		strData.append(this.itemDatas.toString());

		return strData.toString();
	}

	public void reset() {
		try {
			GetRequest request = Unirest.get(this.cLodopServer + "/CLodopfuncs.js");

			HttpResponse response = request.asString();
			if (response.getStatus() == 200) {
				String script = (String) response.getBody();
				script = script.substring(script.indexOf("{") + 1, script.indexOf("altMessageBusy")) + "}";
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("javascript");
				engine.eval(script);
				this.clodop = ((ScriptObjectMirror) engine.get("CLODOP"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() {
		reset();
	}
}

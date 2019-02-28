package com.max.clodop;

import com.max.clodop.entity.PrintResult;
import com.max.clodop.entity.Printers;
import com.max.clodop.entity.Printer;

import java.util.Map;

public class Demo {
	public static void main(String[] args) {
		CLodopClient client = new CLodopClient("http://192.168.51.232:8000");

		Printers printers = client.getPrinter();
		for (Map.Entry entry : printers.getPrinters().entrySet()) {
			System.out.println(
					((Printer) entry.getValue()).getName() + "\t" + ((Printer) entry.getValue()).getPortName());
		}

		PrintResult result = client.print();
		client.isPrintSuccess(result);
	}
}

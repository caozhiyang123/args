package com.smart.test;

import com.smart.args.Args;
import com.smart.exception.ArgsException;

public class Demo {
	private static Args argsarg;
	private static boolean logging;
	private static int intport;
	private static String stringdirectory;
	
	public static void main(String[] args) {
		try {
			argsarg = new Args("l,p#,d*",args);
			logging = argsarg.getBoolean('l');
			intport = argsarg.getInt('p');
			stringdirectory = argsarg.getString('d');
			executeApplication(logging,intport,stringdirectory);
		} catch (ArgsException e) {
			e.printStackTrace();
		}
		
	}

	private static void executeApplication(boolean logging, int intport, String stringdirectory) {
		System.out.println(logging + "," + intport + "," + stringdirectory);
	}
}

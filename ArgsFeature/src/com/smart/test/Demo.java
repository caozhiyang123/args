package com.smart.test;

import java.util.Arrays;

import com.smart.args.Args;
import com.smart.exception.ArgsException;

public class Demo {
	private static Args argsarg;
	private static boolean logging;
	private static int intport;
	private static String stringdirectory;
	private static double doubleV;
	private static String[] stringArr;
	
	public static void main(String[] args) {
		try {
			argsarg = new Args("B,I#,S*,D##,A[*]",args);
			logging = argsarg.getBoolean('B');
			intport = argsarg.getInt('I');
			stringdirectory = argsarg.getString('S');
			doubleV = argsarg.getDouble('D');
			stringArr = argsarg.getStringArray('A');
			executeApplication(logging,intport,stringdirectory,doubleV,stringArr);
		} catch (ArgsException e) {
			e.printStackTrace();
		}
		
	}

	private static void executeApplication(boolean logging, int intport, String stringdirectory,
			double doubleV,String[] stringArr) {
		System.out.println(logging + "," + intport + "," + stringdirectory+","+doubleV+","+
			Arrays.asList(stringArr).toString());
	}
}

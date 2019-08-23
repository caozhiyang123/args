package com.smart.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.smart.exception.ArgsException;
import com.smart.exception.ErrorCode;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
	private String[] StringArr;

	@Override
	public void set(Iterator<String> currentArgument) throws ArgsException {
		try {
			String stringValue = currentArgument.next();
			StringArr = stringValue.split(",");
		} catch (NoSuchElementException e) {
			throw new ArgsException(ErrorCode.MISSING_STRING_ARR);
		}
	}
	
	public static String[] getValue(ArgumentMarshaler am){
		if(am != null && am instanceof StringArrayArgumentMarshaler){
			return ((StringArrayArgumentMarshaler)am).StringArr;
		}else{
			return new String[0];
		}
	}

}

package com.smart.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.smart.exception.ArgsException;
import com.smart.exception.ErrorCode;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
	private boolean booleanValue = false;

	@Override
	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;
		try {
			parameter = currentArgument.next();
			booleanValue = Boolean.parseBoolean(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(ErrorCode.MISSING_DOUBLE);
		} catch (NumberFormatException e){
			throw new ArgsException(ErrorCode.INVALID_DOUBLE, parameter);
		}
	}
	
	public static boolean getValue(ArgumentMarshaler am){
		if(am != null && am instanceof BooleanArgumentMarshaler){
			return ((BooleanArgumentMarshaler)am).booleanValue;
		}else{
			return false;
		}
	}
	
}

package com.smart.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.smart.exception.ArgsException;
import com.smart.exception.ErrorCode;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
	private double doubleValue = 0.0;

	@Override
	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;
		try {
			parameter = currentArgument.next();
			doubleValue = Double.parseDouble(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(ErrorCode.MISSING_DOUBLE);
		} catch (NumberFormatException e){
			throw new ArgsException(ErrorCode.INVALID_DOUBLE, parameter);
		}
	}
	
	public static double getDouble(ArgumentMarshaler am){
		if(am != null && am instanceof DoubleArgumentMarshaler){
			return ((DoubleArgumentMarshaler)am).doubleValue;
		}else{
			return 0;
		}
	}
	
}

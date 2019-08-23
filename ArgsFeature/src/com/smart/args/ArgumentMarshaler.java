package com.smart.args;

import java.util.Iterator;

import com.smart.exception.ArgsException;

public interface ArgumentMarshaler {
	void set(Iterator<String> currentArgument) throws ArgsException;
}

package com.smart.exception;

public class ArgsException extends Exception{
	private char errorArgumentId = '\0';
	private String errorParameter = null;
	private ErrorCode errorCode = ErrorCode.OK;
	
	
	public ArgsException() {}
	public ArgsException(String message) {super(message);}
	
	public ArgsException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public ArgsException( ErrorCode errorCode,String errorParameter) {
		this.errorParameter = errorParameter;
		this.errorCode = errorCode;
	}
	
	public ArgsException( ErrorCode errorCode,char errorArgumentId, String errorParameter) {
		this.errorCode = errorCode;
		this.errorArgumentId = errorArgumentId;
		this.errorParameter = errorParameter;
	}
	
	public char getErrorArgumentId() {
		return errorArgumentId;
	}
	
	public void setErrorArgumentId(char errorArgumentId) {
		this.errorArgumentId = errorArgumentId;
	}
	
	public String getErrorParameter() {
		return errorParameter;
	}
	
	public void setErrorParameter(String errorParameter) {
		this.errorParameter = errorParameter;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public String errorMessage(){
		switch(errorCode){
			case OK:
				return "TILT: Should not get here.";
			case UNEXPECTED_ARGUMENT:
				return String.format("Could not find String parameter for -%c.", errorArgumentId);
			case INVALID_INTEGER:
				return String.format("ARGUMENT -%c expects an integer but was '%s'.", errorArgumentId,errorParameter);
			case MISSING_INTEGER:
				return String.format("Could not find integer parameter for -%c.", errorArgumentId);
			case INVALID_DOUBLE:
				return String.format("Argument -%c expects a doulbe but was '%s'.", errorArgumentId,errorParameter);
			case MISSING_DOUBLE:
				return String.format("Could not find double paramenter for -%c.", errorArgumentId);
			case INVALID_ARGUMENT_NAME:
				return String.format("'%c' is not a valid argument name.", errorArgumentId);
			case INVALID_ARGUMENT_FORMAT:
				return String.format("'%s' is not a valid argument foramt.", errorParameter);
		}
		return "";
	}
}

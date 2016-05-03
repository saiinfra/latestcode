package com.exception;

/**
 * 
 * @author ErrorCode is Having Operations Like Getting Error Number,Error
 *         Code,Setting ErrorCode
 * 
 *
 */
public interface ErrorCode {
	/**
	 * 
	 * @return Error Number
	 */
	public int getNumber();

	/**
	 * 
	 * @return ErrorCode
	 */
	public String getErrorCode();

	/**
	 * 
	 * @param Setting
	 *            errrCode
	 * @return String
	 */
	public String setErrorCode(String errrCode);
}

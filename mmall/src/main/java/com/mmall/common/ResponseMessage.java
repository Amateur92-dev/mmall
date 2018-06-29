package com.mmall.common;

public class ResponseMessage <T>{

	private  Integer code;
	
	private String message;
	
	private T data;
	
	
	/**
	 * 返回成功码
	 * @return
	 */
	

	public static <T> ResponseMessage<T> success(){
		
		return new ResponseMessage<T>(ResponseCode.SUCCESS.getCode());
	}
	
	public  static <T> ResponseMessage<T> successWithMessage(String message){
		
		return new ResponseMessage<T>(ResponseCode.SUCCESS.getCode(),message);
	}
	
	public static <T> ResponseMessage<T> successWithMegAndData(String message,T data){
		
		
		return new ResponseMessage<T>(ResponseCode.SUCCESS.getCode(), message,data);
	}
	
	public static <T> ResponseMessage<T> successWithData(T data){
		
		
		return new ResponseMessage<T>(ResponseCode.SUCCESS.getCode(),data);
	}
	
	
	public static <T> ResponseMessage<T> error() {

		return new ResponseMessage<T>(ResponseCode.FAIL.getCode());
	}

	public  static <T> ResponseMessage<T> errorWithMessage(String message) {

		return new ResponseMessage<T>(ResponseCode.FAIL.getCode(), message);
	}

	/**
	 * 返回状态码
	 * @return
	 */
	public boolean isSuccess(){
		
		return this.code==ResponseCode.SUCCESS.getCode();
	}

	private ResponseMessage(Integer code, String message, T data) {
		
		this.code = code;
		this.setMessage(message);
		this.setData(data);
	}
	
	private ResponseMessage(Integer code) {
		
		this.code = code;
		
	}private ResponseMessage( T data) {
		
		this.setData(data);
	}
	private ResponseMessage(String message) {
		
		this.setMessage(message);
	}
	
	private ResponseMessage(Integer code, T data) {
		super();
		this.code = code;
		this.setData(data);
	}
	
	private ResponseMessage(Integer code, String message) {
		super();
		this.code = code;
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}

package com.mmall.common;

public enum ResponseCode {
	SUCCESS(1, "³É¹¦"),

	FAIL(0, "Ê§°Ü");
	private final Integer code;

	private final String message;

	private ResponseCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}

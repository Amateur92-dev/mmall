package com.mmall.common;

public enum ResponseCode {
	SUCCESS(1, "�ɹ�"),

	FAIL(0, "ʧ��");
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

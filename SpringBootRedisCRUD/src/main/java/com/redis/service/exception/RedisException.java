package com.redis.service.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RedisException extends Exception {

	private static final long serialVersionUID = 1L;

	public RedisException(String message) {
		super(message);
	}

}
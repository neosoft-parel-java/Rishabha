package com.redis.service.exception;

import java.util.Date;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import redis.clients.jedis.exceptions.JedisConnectionException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RedisException.class)
	public ResponseEntity<ErrorDetails> resourceNotFoundException(RedisException ex) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getLocalizedMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RedisConnectionFailureException.class)
	public ResponseEntity<ErrorDetails> redisConnectionFailureExceptionHandler(Exception ex) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Cannot get redis server connection", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(JedisConnectionException.class)
	public ResponseEntity<ErrorDetails> jedisConnectionExceptionHandler(Exception ex) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Cannot get Jedis connection", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globleExceptionHandler(Exception ex) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internal Server Error", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
}
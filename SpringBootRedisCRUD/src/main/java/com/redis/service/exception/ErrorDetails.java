package com.redis.service.exception;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ErrorDetails {

	private Date timestamp;

	private String message;

	private String details;

	public ErrorDetails(Date timestamp, String message, String details) {

		super();

		this.timestamp = timestamp;

		this.message = message;

		this.details = details;

	}

}
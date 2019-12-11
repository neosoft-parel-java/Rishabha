package com.redis.service.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;

	public Student(String id, String name) {
		this.id = id;
		this.name = name;

	}



}

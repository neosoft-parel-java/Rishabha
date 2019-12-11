package com.redis.service.repository;

import java.util.Map;

import com.redis.service.model.Student;

public interface RedisRepository {

	Map<String, Student> findAllStudents();

	void add(Student student);

	void update(Student student);

	void delete(String id);

	Student findById(String id);

}

package com.redis.service.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.redis.service.model.Student;

@Repository
public class RedisRepositoryImpl implements RedisRepository {
	private static final String KEY = "Student";

	private RedisTemplate<String, Student> redisTemplate;
	private HashOperations<String, String, Student> hashOperations;

	@Autowired
	public RedisRepositoryImpl(RedisTemplate<String, Student> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = this.redisTemplate.opsForHash();
	}

	/**
	 * Add student to Redis.
	 */
	public void add(Student student) {
		hashOperations.put(KEY, student.getId(), student);
	}

	/**
	 * update student to Redis if present with respective key.
	 */
	public void update(Student student) {
		hashOperations.put(KEY, student.getId(), student);
	}

	/**
	 * Delete a student in Redis.
	 */
	public void delete(String id) {
		hashOperations.delete(KEY, id);
	}

	/**
	 * finds a student by id
	 */
	public Student findById(String id) {

		return hashOperations.get(KEY, id);
	}

	/**
	 * Return all students
	 */
	public Map<String, Student> findAllStudents() {
		return hashOperations.entries(KEY);
	}

}

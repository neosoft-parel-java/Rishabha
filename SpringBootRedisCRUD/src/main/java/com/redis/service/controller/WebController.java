package com.redis.service.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.redis.service.model.Student;
import com.redis.service.repository.RedisRepository;

@RestController
@RequestMapping("/student")
public class WebController {

	@Autowired
	private RedisRepository redisRepository;

	@GetMapping("/findallkeys")
	public @ResponseBody Map<String, Student> keys() {
		return redisRepository.findAllStudents();
	}

	@GetMapping("/findallvalues")
	public @ResponseBody Map<String, Student> findAll() {
		Map<String, Student> aa = redisRepository.findAllStudents();
		Map<String, Student> map = new HashMap<>();
		for (Entry<String, Student> entry : aa.entrySet()) {
			String key = entry.getKey();
			map.put(key, aa.get(key));
		}
		return map;
	}

	@GetMapping("/findbyid")
	public ResponseEntity<Student> findById(@RequestParam String key) {

		return new ResponseEntity<>(redisRepository.findById(key), HttpStatus.OK);
	}

	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestParam String key, @RequestParam String value) {

		Student student = new Student(key, value);

		redisRepository.add(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<String> update(@RequestParam String key, @RequestParam String value) {

		Student student = new Student(key, value);

		redisRepository.update(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> delete(@RequestParam String key) {
		redisRepository.delete(key);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

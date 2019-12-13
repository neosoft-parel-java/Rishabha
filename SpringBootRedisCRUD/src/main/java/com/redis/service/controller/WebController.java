package com.redis.service.controller;

import java.util.AbstractMap.SimpleEntry;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/student")
@Api(value="Student CRUD Operations", description="Performing CRUD operations with Redis on Student")
public class WebController {

	@Autowired
	private RedisRepository redisRepository;

	@GetMapping("/findallkeys")
	@ApiOperation(value = "List of all available keys", response = Map.class)
	@ApiResponse(code = 200, message = "Successfully retrieved keys")
	public @ResponseBody Map<String, Student> keys() {
		return redisRepository.findAllStudents();
	}

	
	@GetMapping("/findallvalues")
	@ApiOperation(value = "List of all students", response = Map.class)
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
	@ApiOperation(value = "Find student by id", response = Student.class,code=200)
	public ResponseEntity<Student> findById(@RequestParam String key) {

		return new ResponseEntity<>(redisRepository.findById(key), HttpStatus.OK);
	}

	@PostMapping(value = "/add")
	@ApiOperation(value = "Save Student", response = SimpleEntry.class)
	public ResponseEntity<SimpleEntry<String, String>> add(@RequestParam String key, @RequestParam String value) {

		Student student = new Student(key, value);

		redisRepository.add(student);
		return new ResponseEntity<>(new SimpleEntry<String, String>("status", "success"), HttpStatus.OK);
	}

	@PutMapping(value = "/update")
	@ApiOperation(value = "Update Student", response = SimpleEntry.class)
	public ResponseEntity<SimpleEntry<String, String>> update(@RequestParam String key, @RequestParam String value) {

		Student student = new Student(key, value);

		redisRepository.update(student);
		return new ResponseEntity<>(new SimpleEntry<String, String>("status", "success"), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete")
	@ApiOperation(value = "Delete Expert", code = 204)
	public ResponseEntity<String> delete(@RequestParam String key) {
		redisRepository.delete(key);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

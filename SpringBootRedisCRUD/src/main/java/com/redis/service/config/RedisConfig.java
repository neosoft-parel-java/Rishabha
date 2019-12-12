package com.redis.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.redis.service.model.Student;

@Configuration
@ComponentScan("com.redis.service")
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String hostName;

	@Value("${spring.redis.port}")
	private int port;

	/**
	 * This used to configure the connection details such as host/server address and
	 * port.
	 * 
	 * @returns JedisConnectionFactory object
	 */
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {

		/* To connect to a different host and port */
		// JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();		
		// jedisConFactory.setHostName(hostName);
		// jedisConFactory.setPort(port);
		// return jedisConFactory;

		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Student> redisTemplate() {
		final RedisTemplate<String, Student> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Student>(Student.class));
		return template;
	}

}
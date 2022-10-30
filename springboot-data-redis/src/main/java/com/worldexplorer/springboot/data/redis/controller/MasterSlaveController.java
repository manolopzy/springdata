package com.worldexplorer.springboot.data.redis.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterSlaveController {
	
	private static final String KEY = "MASTERSLAVES";

    @Autowired
    private StringRedisTemplate template;

    @GetMapping("/{name}")
    public void addToSet(@PathVariable String name) {
        this.template.opsForSet().add(KEY, name);
    }

    @GetMapping("/get")
    public Set<String> getKeyValues() {
        return this.template.opsForSet().members(KEY);
    }
}

package com.worldexplorer.springboot.data.redis.domain;

import java.io.Serializable;

public interface RedisBaseObject extends Serializable{

	public long getId();
	public <T extends RedisBaseObject> T deserialize(byte[] bytes);
	public byte[] serialize();
	
}

package com.worldexplorer.springboot.data.redis.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * The cluster configuration can also be defined through PropertySource and has
 * the following properties:
 * 
 * spring.redis.cluster.nodes: 
 * 127.0.0.1:7379,
 * 127.0.0.1:7380,
 * 172.18.0.4:6379
 * Comma-delimited list of host:port pairs.
 * spring.redis.cluster.max-redirects: 
 * Number of allowed cluster redirections.
 * 
 * @author tanku
 *
 */
@Component
@PropertySource("classpath:redisCluster.properties")
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterConfigurationProperties {
	
	/*
	 * spring.redis.cluster.nodes[0] = 127.0.0.1:7379 
	 * spring.redis.cluster.nodes[1] = 127.0.0.1:7380 
	 * ...
	 */
	@Value("#{'${nodes}'.split(',')}")
	private List<String> nodes;
	
	@Value("${timeout}")
	private String timeout;
	
	@Value("${max-redirects}")
	private String maxRedirects;
	
	/**
	 * Get initial collection of known cluster nodes in format {@code host:port}.
	 *
	 * @return
	 */
	public List<String> getNodes() {
		return nodes;
	}

	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}
}

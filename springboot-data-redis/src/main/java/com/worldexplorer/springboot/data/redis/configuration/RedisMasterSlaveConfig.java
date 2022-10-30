package com.worldexplorer.springboot.data.redis.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import io.lettuce.core.ReadFrom;

@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisMasterSlaveConfig {

	private RedisInstance master;
    private List<RedisInstance> slaves;

    RedisInstance getMaster() {
        return master;
    }

    void setMaster(RedisInstance master) {
        this.master = master;
    }

    List<RedisInstance> getSlaves() {
        return slaves;
    }

    void setSlaves(List<RedisInstance> slaves) {
        this.slaves = slaves;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = 
        		LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();
        RedisStaticMasterReplicaConfiguration staticMasterReplicaConfiguration = 
        		new RedisStaticMasterReplicaConfiguration(this.getMaster().getHost(), this.getMaster().getPort());
        this.getSlaves().forEach(slave -> staticMasterReplicaConfiguration.addNode(slave.getHost(), slave.getPort()));
        return new LettuceConnectionFactory(staticMasterReplicaConfiguration, clientConfig);
    }

    private static class RedisInstance {

        private String host;
        private int port;

        String getHost() {
            return host;
        }

        void setHost(String host) {
            this.host = host;
        }

        int getPort() {
            return port;
        }

        void setPort(int port) {
            this.port = port;
        }
    }
}

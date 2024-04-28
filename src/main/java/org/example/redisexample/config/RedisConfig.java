package org.example.redisexample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration(exclude = {
    JmxAutoConfiguration.class
})
public class RedisConfig {

  @Value("${jedis.pool.host}")
  private String redisHost;

  @Value("${jedis.pool.port}")
  private int redisPort;

  @Bean
  public JedisPool jedis() {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(100);
    return new JedisPool(poolConfig, redisHost, redisPort);
  }
}
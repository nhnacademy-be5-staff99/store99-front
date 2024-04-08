package com.nhnacademy.store99.front.config;

import com.nhnacademy.store99.front.config.property.RedisProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * 레디스 설정
 *
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@Configuration
public class RedisConfig {
    private final RedisProperties redisProperties;

    /**
     * 레디스 연결 설정
     *
     * @return LettuceConnectionFactory
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

}

package com.zjc.redis.config;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangjiacheng
 * @description: TODO
 * @date 2019/7/12 11:18
 */
@Component
@Setter
@Getter
@ConfigurationProperties("spring.redis")
public class RedisConfig {

    private String host;
    private String port;
    private String password;

}

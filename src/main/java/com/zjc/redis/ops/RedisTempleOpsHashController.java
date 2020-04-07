package com.zjc.redis.ops;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author zhangjiacheng
 * @description:
 * redis基本数据类型操作之hash
 * 使用: redisTemplate
 * @date 2020/3/30 10:04
 */
@RestController
@RequestMapping("hash")
@Slf4j
public class RedisTempleOpsHashController {

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value={"add"},method = RequestMethod.POST)
    public void add(){

        //put[putAll]
        //putIfAbsent
        redisTemplate.opsForHash().put("hash","map1","hashz");
        redisTemplate.opsForHash().put("hash","map2","hsahj");
        redisTemplate.opsForHash().put("hash","map3","hashc");
    }


    @RequestMapping(value={"get"},method = RequestMethod.POST)
    public void get(){

        //(1)获取所有的key
        Set<String> set =  redisTemplate.opsForHash().keys("hash");

        for(String str :set ){
            log.info("hash中所有的key:【{}】",str);
        }

        //(2)获取所有的value
        List<String > list =  redisTemplate.opsForHash().values("hash");
        for(String str :list){
            log.info("hash中所有的value：【{}】",str);
        }

        //(3)根据key获取值
        log.info("根据key获取值：【{}】",redisTemplate.opsForHash().get("hash","map1"));


    }

    @RequestMapping(value={"del"},method = RequestMethod.POST)
    public void del(){
        //(1)根据key移除值
        redisTemplate.opsForHash().delete("hash","map2");
    }


    /**
     * entries 用于以Map的格式获取一个Hash键的所有值
     *hashkey 用于获取一个Hash键中是否含有某个键
     * size 用于获取一个Hash键中包含的键的数量。
     * multiGet
     * increment
     * scan 获取所以匹配条件的Hash键中key的值
     */
}

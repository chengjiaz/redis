package com.zjc.redis.ops;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjiacheng
 * @description:
 * redis基本数据类型操作之string
 * 使用: redisTemplate
 * @date 2020/3/30 10:04
 */
@RestController
@RequestMapping("str")
@Slf4j
public class RedisTempleOpsStrController {



    @Autowired
  private   RedisTemplate redisTemplate;

    /**
     * 新增
     * setnx 如果 key 不存在就执行 set 创建
     * 批量: mset
     */
    @RequestMapping(value={"add"},method = RequestMethod.POST)
    public void add(){
        redisTemplate.opsForValue().set("name","zjc");
    }


    /**
     * 获取
     * 批量:mget
     */
    @RequestMapping(value={"get"},method = RequestMethod.POST)
    public void get(){
        String getValue = (String )redisTemplate.opsForValue().get("name");
        log.info("获取的value=【{}】",getValue);
    }


    /**
     * 删除
     */
    @RequestMapping(value={"del"},method = RequestMethod.POST)
    public void del(){
                                      //判断key是否存在
        boolean getKey = redisTemplate.hasKey("name");
        if(getKey ){
            Boolean delValue = redisTemplate.delete("name");

            if(delValue){
                log.info("成功删除key:【{}】","name");
            }
        }

    }


    /**
     * 过期
     * expire
     * setex = set+expire
     *
     */
    @RequestMapping(value={"expire"},method = RequestMethod.POST)
    public void expire(){

      redisTemplate.expire("name",5, TimeUnit.SECONDS);
    }

    /**
     *incr  +1;
     * incrBy 加指定数值;
     * decr -1;
     * decrBy 减去指定的数值
     * keys:查找匹配的key值，返回一个Set集合类型
     * rename：修改redis中key的名称
     * type:返回传入key所存储的值的类型
     * renameIfAbsent：如果旧值存在时，将旧值改为新值
     * randomKey
     * getExpire：返回当前key所对应的剩余过期时间
     * persist
     * move
     * getAndSet
     * multiGet
     * append
     */





}

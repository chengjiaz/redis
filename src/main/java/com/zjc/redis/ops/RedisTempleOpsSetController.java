package com.zjc.redis.ops;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author zhangjiacheng
 * @description:
 * redis基本数据类型操作之set
 * 使用: redisTemplate
 * @date 2020/3/30 10:04
 */
@RestController
@RequestMapping("set")
@Slf4j
public class RedisTempleOpsSetController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value={"add"},method = RequestMethod.POST)
    public void add(){
        redisTemplate.opsForSet().add("set","setz","setj","setc");

        //size
        log.info("存放个数:【{}】",redisTemplate.opsForSet().size("set"));

        //isMember 判断元素是否是集合的成员
        log.info("【{}】",redisTemplate.opsForSet().isMember("set","zjc"));

    }


    @RequestMapping(value={"get"},method = RequestMethod.POST)
    public void get(){
       Set<String> set =  redisTemplate.opsForSet().members("set");

       for(String str :set){
           log.info("for循环方式:【{}】",str);
       }

        Cursor<Object> curosr =redisTemplate.opsForSet().scan("set", ScanOptions.NONE);
       while (curosr.hasNext()){
           log.info("scan方式:【{}】",curosr.next());
       }


        //randomMember
        // randomMembers
       log.info("随机获取:【{}】",redisTemplate.opsForSet().randomMember("set"));
    }

    @RequestMapping(value={"del"},method = RequestMethod.POST)
    public void del(){
        redisTemplate.opsForSet().remove("set","setz");
    }

    /**
     *(1)pop  移除并返回集合中的一个随机元素
     * (2)move
     *(3)intersect  key对应的无序集合与otherKey|(多个)对应的无序集合求交集
     * (4)intersectAndStore(K key, K otherKey, K destKey)
     * key无序集合与otherkey无序集合的交集存储到destKey无序集合中
     * (5)union  key无序集合与otherKey|(多个)无序集合的并集
     * (6)unionAndStore  key无序集合与otherkey|(多个)无序集合的并集存储到destKey无序集合中
     * (7) difference  key无序集合与otherKey|(多个)无序集合的差集
     * (8)differenceAndStore key无序集合与otherkey|(多个)无序集合的差集存储到destKey无序集合中
     * (9)distinctRandomMembers 获取多个key无序集合中的元素（去重），count表示个数
     */

}

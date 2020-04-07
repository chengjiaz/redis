package com.zjc.redis.ops;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangjiacheng
 * @description:
 * redis基本数据类型操作之zset
 * 使用: redisTemplate
 * @date 2020/3/30 10:04
 */
@RestController
@RequestMapping("zset")
@Slf4j
public class RedisTempleOpsZsetController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value={"add"},method = RequestMethod.POST)
    public void add(){
        ZSetOperations.TypedTuple zset1 =  new DefaultTypedTuple("zsetz",1.0);
        ZSetOperations.TypedTuple zset2 =  new DefaultTypedTuple("zsetj",2.0);
        ZSetOperations.TypedTuple zset3 =  new DefaultTypedTuple("zsetc",3.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();

        tuples.add(zset1);
        tuples.add(zset2);
        tuples.add(zset3);

        redisTemplate.opsForZSet().add("testzset",tuples);


        //
        log.info("新增集合长度（size）【{}】",redisTemplate.opsForZSet().size("testzset"));

        //
        log.info("新增集合长度（zcard）【{}】",redisTemplate.opsForZSet().zCard("testzset"));


    }


    @RequestMapping(value={"get"},method = RequestMethod.POST)
    public void get(){

        log.info("获取指定成员的分数:【{}】",redisTemplate.opsForZSet().rank("testzset","zsetz"));

        Set<String> getValue = redisTemplate.opsForZSet().range("testzset",0,-1);

        log.info("===========【for循环 方式】=============");
        for(String str : getValue){
            log.info("获取的元素:【{}】;分数:【{}】",str,redisTemplate.opsForZSet().score("testzset",str));

        }



    }

    @RequestMapping(value={"del"},method = RequestMethod.POST)
    public void del(){
        redisTemplate.opsForZSet().remove("testzset","zsetz");

        Set<String> getValue = redisTemplate.opsForZSet().range("testzset",0,-1);
        for(String str : getValue){
            log.info("删除之后的元素:【{}】",str);
        }
    }

    /**
     * incrementScore  增加元素的score值，并返回增加后的值
     * reverseRank 返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列
     * scan
     * reverseRank(从大到小)
     * count 通过分数返回有序集合指定区间内的成员个数
     * removeRange 移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     * unionAndStore|(多个) 计算给定的一个有序集的并集，并存储在新的 destKey中，key相同的话会把score值相加
     * intersectAndStore|(多个) 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
     * rangeWithScores 由小到大
     * reverseRange |(V)   /reverseRangeWithScores |(TypedTuple)
     * rangeByScore|(V)[reverseRangeByScore]/  rangeByScoreWithScores|(TypedTuple)[reverseRangeByScoreWithScores]（由大到小）
     */

}

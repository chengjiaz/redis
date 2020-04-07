package com.zjc.redis.ops;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjiacheng
 * @description:
 * redis基本数据类型操作之list
 * 使用: redisTemplate
 * @date 2020/3/30 10:04
 */
@RestController
@RequestMapping("list")
@Slf4j
public class RedisTempleOpsListController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增
     *
     *
     */
    @RequestMapping(value={"add"},method = RequestMethod.POST)
    public void add(){

        //(1)leftPush 从列表左侧插入元素[rightPush=从列表右侧插入元素]

        redisTemplate.opsForList().leftPush("list1",'z');
        redisTemplate.opsForList().leftPush("list1",'j');
        redisTemplate.opsForList().leftPush("list1",'c');

       //(2) leftPushAll[rightPushAll]
       redisTemplate.opsForList().leftPushAll("list2","z","j","c");

       //(3) leftPushAll[rightPushAll]
        List list= new ArrayList();
        list.add("z");
        list.add("j");
        list.add("c");
        redisTemplate.opsForList().leftPushAll("list3",list);

    }

    /**
     * 获取
     */
    @RequestMapping(value={"get"},method = RequestMethod.POST)
    public void get(){

       //(1)range 获取列表指定范围内的元素(start开始位置, 0是开始位置，end 结束位置, -1返回所有)
        List list = redisTemplate.opsForList().range("list1",0,-1);
       log.info("获取list中指定位置的元素:【{}】",list.size());
       //(2) index 通过索引获取列表中的元素
       String getValue = (String)redisTemplate.opsForList().index("list2",1);
       log.info("获取list2中指定位置的元素:【{}】",getValue);
    }


    /**
     * 删除
     */
    @RequestMapping(value={"del"},method = RequestMethod.POST)
    public void del(){

        //(1)leftPop 移除并获取列表中第一个元素 [rightPop]
        log.info("list1的元素个数:【{}】",redisTemplate.opsForList().range("list1",0,-1).size());
        redisTemplate.opsForList().leftPop("list1");
    }


    /**
     *set  设置指定索引处元素的值
     * rightPopAndLeftPush 从一个队列的右边弹出一个元素并将这个元素放入另一个指定队列的最左边
     * trim 将List列表进行剪裁
     * remove(K key, long count, Object value) ：
     * conut = 0，删除所有匹配的元素
     * count > 0 删除匹配元素开始，从左到右最多count个元素
     * count < 0 删除匹配元素开始，从右到左最多count个元素
     */

}

package com.jrl.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author:Crady
 * @date:2018/3/8 18:08
 * @desc: redis工具类
 **/
@Component
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
      *@author:Crady
      *@date: 2018/03/09 09:30:59
      *@params:  * @param key
     * @param obj
      *@return: void
      *@desc:
    **/
    public void setValue(String key,Object obj){
        redisTemplate.opsForValue().set(key,obj);
    }

    /**
     *设置缓存
     * @param key  键
     * @param obj  值
     * @param expiration  过期时间-单位秒
     */
    public void setValue(String key,Object obj,long expiration){
        redisTemplate.opsForValue().set(key,obj,expiration, TimeUnit.SECONDS);
    }

    /**
     * 获取redis缓存
     * @param key 获取指定key缓存
     * @return
     */
    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 清理redis指定key的数据
     * @author:Crady
     * @param key 删除指定key
     */
    public void remove(String key){
        if(StringUtils.isBlank(key)){
            throw new RuntimeException("清除指定key的缓存数据键不能为空");
        }
        logger.info("清除redis键值为[{}]的缓存数据",key);
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * 清除所有redis缓存数据
     * @author:Crady
     */
    public void removeAll(){
        logger.info("清除所有redis缓存数据...");
        Set<String> keys = redisTemplate.opsForValue().getOperations().keys("*");
        redisTemplate.opsForValue().getOperations().delete(keys);
    }

}

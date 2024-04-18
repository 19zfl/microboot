package com.yootk.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName:RedisService
 * @Description: 继承redis缓存
 * @Author:zfl19
 * @CreateDate:2024/3/30 18:03
 */

@Component
public class RedisService {

    /**
     * StringRedisTemplate它是redisTemplate的子类，而且独有特点：它保存的数据key和value都是string
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 向Redis中存储 key-value键值对数据，value的类型为object
     *
     * @param key
     * @param value
     */
    public void setStringKeyAndValue(String key, Object value) {

        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value));
    }

    /**
     * 保存key-value键值对到 redis,存储的value是字符串类型
     *
     * @param key
     * @param value
     */
    public void setStringKeyAndValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储的数据，多了一个 有效时间
     *
     * @param key
     * @param value
     * @param minute
     */
    public void setStringKeyAndValue(String key, Object value, long minute) {
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value), minute, TimeUnit.MINUTES);
    }

    public void setStringKeyAndValue(String key, String value, long minute) {
        stringRedisTemplate.opsForValue().set(key, value, minute, TimeUnit.MINUTES);
    }

    /**
     * 功能说明 取出对象并转换为对象类型
     *
     * @param key
     * @param clazz
     * @return T
     * @author caiwen
     * @date 2020/8/22
     */
    //传入的这个数据类型，可能是2种类型：1string; 2object，如果是object，要进行泛型转换
    public <T> T getKeyObjectValue(String key, Class<T> clazz) {
        T t = null;
        String s = stringRedisTemplate.opsForValue().get(key);//json格式的字符串//{"name":"张三","age":22,"address":"湖北武汉长城科技园"}
        if (StringUtils.isBlank(s)) {
            return null;
        }
        //下面这个判断，是判断传入的Class类型是什么
        //clazz.getName() 获取传入的参数clazz的数据类型
        if (StringUtils.equalsIgnoreCase(clazz.getName(), "java.lang.string")) {
            t = (T) s;
        } else if (StringUtils.isNotBlank(s)) {
            t = JSONObject.parseObject(s, clazz);
        }
        return t;
    }

    /**
     * 删除
     * @param key
     */
    public void deleteRedisByKey(String key) {
        stringRedisTemplate.delete(key);
    }

}

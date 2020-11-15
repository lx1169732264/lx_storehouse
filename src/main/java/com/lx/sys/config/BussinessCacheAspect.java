package com.lx.sys.config;

import com.alibaba.fastjson.JSON;
import com.lx.bus.domain.Goods;
import com.lx.bus.domain.Inport;
import com.lx.bus.service.GoodsService;
import com.lx.bus.vo.InportVo;
import com.lx.sys.common.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

/**
 * 库存量切入
 *
 * @author boss
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class BussinessCacheAspect {

    private static final String POINTCUT_GOODS_UPDATE = "execution(* com.lx.bus.service.impl.GoodsServiceImpl.updateGoods(..))";

    /**
     * RedisTemplate在ioc容器中是<Object,Object>
     * Autowired是根据类型进行匹配的,所以无法注入
     * Resource是根据名字进行匹配,@Component(value="默认为类名小写")
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void initSerializable() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }

    /**
     * 更新库存切入
     */
    @Around(POINTCUT_GOODS_UPDATE)
    public Object cacheInportUpdate(ProceedingJoinPoint joinPoint) {
        initSerializable();
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        //取出第一个参数
        Goods goods = (Goods) joinPoint.getArgs()[0];
        if (goods.getNumber() > goods.getUppernum()) {
            opsForValue.set(Constant.UPPER_PROFIX + goods.getId(), goods);
        } else if (goods.getNumber() < goods.getLowernum()) {
            opsForValue.set(Constant.LOWER_PROFIX + goods.getId(), goods);
        }else {
            redisTemplate.delete(Constant.UPPER_PROFIX + goods.getId());
            redisTemplate.delete(Constant.LOWER_PROFIX + goods.getId());
        }
        return goods;
    }
}
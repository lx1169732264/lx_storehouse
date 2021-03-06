//package com.lx.sys.config;
//
//import com.lx.bus.domain.Goods;
//import com.lx.sys.common.Constant;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * 库存量切入
// *
// * @author boss
// */
//@Aspect
//@Component
//@EnableAspectJAutoProxy
//public class BussinessCacheAspect {
//
//    private static final String POINTCUT_GOODS_UPDATE = "execution(* com.lx.bus.service.impl.GoodsServiceImpl.updateGoods(..))";
//
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
//
//    public void initSerializable() {
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//    }
//
//    /**
//     * 更新库存切入
//     */
//    @Around(POINTCUT_GOODS_UPDATE)
//    public Object cacheInportUpdate(ProceedingJoinPoint joinPoint) {
//        initSerializable();
//        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
//        //取出第一个参数
//        Goods goods = (Goods) joinPoint.getArgs()[0];
//        if (goods.getNumber() > goods.getUppernum()) {
//            opsForValue.set(Constant.UPPER_PROFIX + goods.getId(), goods);
//        } else if (goods.getNumber() < goods.getLowernum()) {
//            opsForValue.set(Constant.LOWER_PROFIX + goods.getId(), goods);
//        }else {
//            redisTemplate.delete(Constant.UPPER_PROFIX + goods.getId());
//            redisTemplate.delete(Constant.LOWER_PROFIX + goods.getId());
//        }
//        return goods;
//    }
//}
package com.lx.bus.service.impl;

import com.lx.bus.domain.Goods;
import com.lx.bus.vo.GoodsVo;
import com.lx.sys.common.Constant;
import com.lx.sys.common.DataGridView;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lx
 */
@Service
public class WarningServiceImpl {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void initSerializable() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }

    public DataGridView loadAllWarning(GoodsVo goodsVo) {
        initSerializable();
        String prefix = (0 == goodsVo.getPrefix() ? Constant.UPPER_PROFIX : Constant.LOWER_PROFIX) + "*";
        List<Object> list = redisTemplate.opsForValue().multiGet(redisTemplate.keys(prefix));

        if (null == list || list.get(0) == null) {
            return new DataGridView(-1L, "没有数据");
        }
        return new DataGridView(goodsVo.getLimit().longValue(), list.stream().map(i -> (Goods) i).filter(i -> {
            boolean flag = true;
            if (null != goodsVo.getProviderid()) {
                flag = i.getProviderid().equals(goodsVo.getProviderid());
            }
            return flag;
        }).skip(goodsVo.getLimit() * (goodsVo.getPage() - 1)).limit(goodsVo.getLimit()).collect(Collectors.toList()));
    }

    public void stockWarning(Goods goods) {
        initSerializable();
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        //取出第一个参数
        if (goods.getNumber() > goods.getUppernum()) {
            opsForValue.set(Constant.UPPER_PROFIX + goods.getId(), goods);
        } else if (goods.getNumber() < goods.getLowernum()) {
            opsForValue.set(Constant.LOWER_PROFIX + goods.getId(), goods);
        }else {
            redisTemplate.delete(Constant.UPPER_PROFIX + goods.getId());
            redisTemplate.delete(Constant.LOWER_PROFIX + goods.getId());
        }
    }
}

package com.example.seckill.dao;

import com.example.seckill.dataobject.OrderDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDOMapper {
    OrderDO selectById(String id);

    int insertSelective(OrderDO orderDO);
}

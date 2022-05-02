package com.example.seckill.dao;

import com.example.seckill.dataobject.PromoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PromoDOMapper {
    PromoDO selectByPromoId(Integer id);
    List<PromoDO> selectByItemId(Integer itemId);
}

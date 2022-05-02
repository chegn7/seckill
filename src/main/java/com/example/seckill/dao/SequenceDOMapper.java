package com.example.seckill.dao;

import com.example.seckill.dataobject.SequenceDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SequenceDOMapper {
    SequenceDO selectSequenceDOByName(String name);

    int updateSequenceDO(SequenceDO sequenceDO);
}

package com.example.seckill.dao;

import com.example.seckill.dataobject.ItemDO;
import com.example.seckill.dataobject.ItemDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ItemDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    long countByExample(ItemDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    int deleteByExample(ItemDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    int insert(ItemDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    int insertSelective(ItemDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    List<ItemDO> selectByExample(ItemDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    ItemDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    int updateByExampleSelective(@Param("row") ItemDO row, @Param("example") ItemDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    int updateByExample(@Param("row") ItemDO row, @Param("example") ItemDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    int updateByPrimaryKeySelective(ItemDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun May 01 17:08:56 CST 2022
     */
    int updateByPrimaryKey(ItemDO row);

    int updateSalesByPrimaryKey(Integer id, Integer sales);

    List<ItemDO> listItem();
}
package com.example.seckill.dao;

import com.example.seckill.dataobject.UserDO;
import com.example.seckill.dataobject.UserDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    long countByExample(UserDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    int deleteByExample(UserDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    int insert(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    int insertSelective(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    List<UserDO> selectByExample(UserDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    UserDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    int updateByExampleSelective(@Param("row") UserDO row, @Param("example") UserDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    int updateByExample(@Param("row") UserDO row, @Param("example") UserDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    int updateByPrimaryKeySelective(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Fri Apr 29 23:33:15 CST 2022
     */
    int updateByPrimaryKey(UserDO row);

    UserDO selectByTelephone(String telephone);
}
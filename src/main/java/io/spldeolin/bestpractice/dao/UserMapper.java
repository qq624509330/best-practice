package io.spldeolin.bestpractice.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import io.spldeolin.bestpractice.component.Mapper;
import io.spldeolin.bestpractice.po.UserPo;

/**
 * 表映射：用户
 *
 * @author Deolin
 */
public interface UserMapper extends Mapper<UserPo> {

    /**
     * Statement映射：列举全部“用户”
     */
    List<UserPo> listCustom();

    /**
     * Statement映射：指定insert_time字段，插入一个“用户”
     */
    void insertCustom(Date insert_time);

    /**
     * Statement映射：通过nickname字段，检索“用户”
     */
    List<UserPo> getUserByNickname(String nickname);

    /**
     * Statement映射：通过password字段，检索“用户”
     */
    List<UserPo> getUserByPassword(String password);

    /**
     * Statement映射：指定nickname和password字段，以系统时间作为insert_time，插入一个“用户”
     */
    void insertUser(@Param("map") Map<String, Object> map);

    /**
     * 查询一个用户
     */
    UserPo getCustom(Integer id);

    void insert1(UserPo po);

}

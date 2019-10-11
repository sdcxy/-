package com.github.sdcxy.mybatis.mapper;

import com.github.sdcxy.entity.User;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/10 0:21
 **/
public interface UserMapper {

    User selectUser(int id);
}

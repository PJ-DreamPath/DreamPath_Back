package com.korit.dreampath_back.mapper;


import com.korit.dreampath_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAllUser();

    User selectUserById(int userId);

    User selectUserByUserName(String userName);

    int deleteById(int userId);

}

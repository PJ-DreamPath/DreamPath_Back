package com.korit.dreampath_back.mapper;


import com.korit.dreampath_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insert(User user);
    User selectById(int userId);
    User selectByUsername(String username);
    User selectByNickname(String nickname);
}

package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUserMapper {

    List<User> findAll();
    String findUserRoleByUserId(int userId);


    int deleteById(int userId);
}

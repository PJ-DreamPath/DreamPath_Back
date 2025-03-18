package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminUserRepository {

    @Autowired
    private AdminUserMapper adminUserMapper;

    public String findUserRoleName(int userId) {
        String userRoleName = adminUserMapper.findUserRoleByUserId(userId);

        return userRoleName;
    }
    public Optional<List<User>> findAll() {
        List<User> foundAllUsers = adminUserMapper.findAll();
        return foundAllUsers.isEmpty() ? Optional.empty() : Optional.of(foundAllUsers);
    }

    public Optional<Boolean>deleteById(int userId) {
        return adminUserMapper.deleteById(userId) < 1 ? Optional.empty() : Optional.of(true);
    }
}

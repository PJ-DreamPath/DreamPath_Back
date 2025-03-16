package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public Optional<List<User>> findAll() {
        List<User> foundUsers = userMapper.selectAllUser();

        return foundUsers.isEmpty() ? Optional.empty() : Optional.of(foundUsers);
    }

    public Optional<User> findById(int userId) {
        return Optional.ofNullable(userMapper.selectUserById(userId));
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userMapper.selectUserByUserName(username));
    }

    public Optional<Boolean> deleteById(int userId) {
        return userMapper.deleteById(userId) < 1 ? Optional.empty() : Optional.of(true);
    }
}

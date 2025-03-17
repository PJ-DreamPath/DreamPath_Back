package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private UserMapper userMapper;


    public Optional<User> findById(int userId) {
        return Optional.ofNullable(userMapper.selectById(userId));
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userMapper.selectByUsername(username));
    }

    public Optional<User> findByNickname(String nickname) {
        return Optional.ofNullable(userMapper.selectByNickname(nickname));
    }

    public User save(User user){
        System.out.println(user+"!!!!!!!!!!!!!!!!!");
        userMapper.insert(user);
        return user;
    }
}

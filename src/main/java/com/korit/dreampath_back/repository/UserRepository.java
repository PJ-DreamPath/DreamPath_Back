package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public Optional<User> findByPassword(String password) {
        return Optional.ofNullable(userMapper.selectByPassword(password));
    }

    public Optional<User> findByNickname(String nickname) {
        return Optional.ofNullable(userMapper.selectByNickname(nickname));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userMapper.selectByEmail(email));
    }

    public Optional<User> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(userMapper.selectByAccountNumber(accountNumber));
    }

    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(userMapper.selectByPhoneNumber(phoneNumber));
    }

    public Optional<User> findByAddress(String address) {
        return Optional.ofNullable(userMapper.selectByAddress(address));
    }

    public Optional<User> findByProfileImg(String profileImg) {
        return Optional.ofNullable(userMapper.selectByProfileImg(profileImg));
    }

    public Optional<User> findByTicket(String ticketId) {
        return Optional.ofNullable(userMapper.selectByTicketId(ticketId));
    }

    public Optional<User> findByStarPoint(String starPoint) {
        return Optional.ofNullable(userMapper.selectByStarPoint(starPoint));
    }

    public Optional<User> findByRoleList(String roleList) {
        return Optional.ofNullable(userMapper.selectByRoleList(roleList));
    }


    public void updateProfileImg(int userId, String profileImg) {
        userMapper.updateProfileImgById(userId, profileImg);
    }

    public void updateNickname(int userId, String nickname) {
        userMapper.updateNicknameById(userId, nickname);
    }

    public void updatePassword(int userId, String password) {
        userMapper.updatePasswordById(userId, password);
    }


    public void updateEmail(int userId, String email) {
        userMapper.updateEmailById(userId, email);
    }



    public User save(User user){
        System.out.println(user+"!!!!!!!!!!!!!!!!!");
        userMapper.insert(user);
        return user;
    }
}

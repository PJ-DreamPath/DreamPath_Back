package com.korit.dreampath_back.service;

import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.repository.UserRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() throws NotFoundException {
        return userRepository.findAll()
                .orElseThrow(() -> new NotFoundException("회원 정보가 존재하지 않습니다."));
    }

    public User getUserById(int userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 회원 ID 입니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteUserById(int userId) throws NotFoundException {
        return userRepository.deleteById(userId)
                .orElseThrow(() -> new NotFoundException("해당 회원 ID는 존재하지 않습니다."));
    }
}

package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.response.RespAdminUserDto;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.repository.AdminUserRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    public String getUserRoleName(int userId) {
        String userRoleName = adminUserRepository.findUserRoleName(userId);
        System.out.println(userRoleName);
        return userRoleName;
    }


    public List<RespAdminUserDto> getAllUser() throws NotFoundException {
        Optional<List<User>> optionalUsers = adminUserRepository.findAll();

        List<User> users = optionalUsers.orElseThrow(() -> new NotFoundException("존재하는 회원이 없습니다."));
        for(User user : users) {
        }
        List<RespAdminUserDto> dtos = new ArrayList<>();
        for(User user : users) {
            String userRoleName = getUserRoleName(user.getUserId());
            RespAdminUserDto dto = RespAdminUserDto.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .roleType(userRoleName)
                    .nickname(user.getNickname())
                    .createdAt(user.getCreatedAt())
                    .phoneNumber(user.getPhoneNumber())
                    .remainPoint(user.getRemainPoint())
                    .build();
            dtos.add(dto);
        }

        return dtos;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteUser(int userId) throws NotFoundException {
        return adminUserRepository.deleteById(userId)
                .orElseThrow(() -> new NotFoundException("해당 회원 ID가 존재하지 않습니다."));
    }
}

package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.ReqLoginDto;
import com.korit.dreampath_back.dto.request.ReqSignupDto;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.entity.UserRole;
import com.korit.dreampath_back.exception.DuplicatedValueException;
import com.korit.dreampath_back.exception.FieldError;
import com.korit.dreampath_back.repository.UserRepository;
import com.korit.dreampath_back.repository.UserRoleRepository;
import com.korit.dreampath_back.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public boolean duplicatedUsername(String username) {
        System.out.println("서비스 duplicatedUsername 호출");
        return userRepository.findByUsername(username).isPresent(); }

    public boolean duplicatedNickname(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    @Transactional(rollbackFor = Exception.class)
    public User save( ReqSignupDto dto){
        System.out.println("서비스 save 호출");
        if(duplicatedUsername(dto.getUsername())){
            throw new DuplicatedValueException(List.of(FieldError.builder()
                    .field("username")
                    .message("이미 존재하는 사용자입니다.")
                    .build()));
        }
        if(duplicatedNickname(dto.getNickname())){
            throw new DuplicatedValueException(List.of(FieldError.builder()
                    .field("nickname")
                    .message("이미 존재하는 닉네임입니다.")
                    .build()));
        }
        User user = User.builder()
                .username(dto.getUsername())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .build();
        userRepository.save(user);

        UserRole userRole = UserRole.builder()
                .userId(user.getUserId())
                .roleId(dto.getRoleId())
                .build();
        userRoleRepository.save(userRole);
        return user;
    }

    public String login(ReqLoginDto dto) {
        User user = userRepository
                .findByUsername(dto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 확인하세요."));

        if(!bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 다시 확인하세요.");
        }

        Date expires = new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 7));

        return jwtUtil.generateToken(
                user.getUsername(),
                Integer.toString(user.getUserId()),
                expires);
    }
}

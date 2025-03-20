package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.User.ReqUserUpdateDto;
import com.korit.dreampath_back.dto.response.User.RespUserDto;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    @Operation(summary = "사용자 정보 조회")
    public ResponseEntity<RespUserDto> getLoginUser(@AuthenticationPrincipal PrincipalUser principalUser) {

        if (principalUser != null && principalUser.getUser().getProfileImg() == null) {
            principalUser.getUser().setProfileImg("default.png");
        }

        RespUserDto response = userService.getUserInfo(principalUser.getUser().getUserId());
        int roleId = principalUser.getRoleId();
        response.setRoleId(roleId);


        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/user/profile/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "프로필 이미지 수정")
    public ResponseEntity<?> changeProfileImg(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestPart("file") MultipartFile file) {

        userService.updateProfileImg(principalUser.getUser(), file);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/profile/nickname")
    @Operation(summary = "닉네임 수정")
    public ResponseEntity<?> changeNickname(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody ReqUserUpdateDto reqUserUpdateDto) {

        userService.updateNickname(principalUser.getUser(), reqUserUpdateDto.getNickname());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/profile/password")
    @Operation(summary = "비밀번호 수정")
    public ResponseEntity<?> changePassword(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody ReqUserUpdateDto reqUserUpdateDto) {

        userService.updatePassword(principalUser.getUser(), reqUserUpdateDto.getPassword());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/profile/email")
    @Operation(summary = "이메일 수정")
    public ResponseEntity<?> changeEmail(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody ReqUserUpdateDto reqUserUpdateDto) {

        userService.updateEmail(principalUser.getUser(), reqUserUpdateDto.getEmail());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user")
    @Operation(summary = "회원탈퇴")
    public ResponseEntity<?> deleteUser(
            @AuthenticationPrincipal PrincipalUser principalUser) {

        userService.deleteUser(principalUser.getUser());
        return ResponseEntity.ok().build();
    }

}

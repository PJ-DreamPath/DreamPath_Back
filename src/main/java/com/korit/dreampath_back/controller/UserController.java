package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user/profile/img")
    public ResponseEntity<?> changeProfileImg(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestPart MultipartFile file) {

        userService.updateProfileImg(principalUser.getUser(), file);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/profile/nickname")
    public ResponseEntity<?> changeNickname(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody
            ) {
        String nickname = requestBody.get("nickname");
        userService.updateNickname(principalUser.getUser(), nickname);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/profile/password")
    public ResponseEntity<?> changePassword(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody
    ) {
        String password = requestBody.get("password");
        userService.updatePassword(principalUser.getUser(), password);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/profile/email")
    public ResponseEntity<?> changeEmail(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody
    ) {
        String email = requestBody.get("email");
        userService.updateEmail(principalUser.getUser(), email);
        return ResponseEntity.ok().build();
    }
}

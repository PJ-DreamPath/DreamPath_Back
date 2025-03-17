package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.ReqSignupDto;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Operation(summary = "회원가입", description = "회원가입 설명")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody ReqSignupDto dto) {
        System.out.println("컨트롤러 호출");
        return ResponseEntity.ok().body(userService.save(dto));
    }
}

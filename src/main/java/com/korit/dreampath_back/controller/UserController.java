package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.response.common.SuccessResponseDto;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.constraints.Min;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@Api(tags = " ")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/admin/list/user")
    @ApiOperation(value = "회원 전체 조회", notes = "회원 전체를 조회 합니다.")
    public ResponseEntity<SuccessResponseDto<List<User>>> getUsers() throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.getAllUsers()));
    }

    @GetMapping("/api/admin/list/user/{userId}")
    @ApiOperation(value = "회원 ID 조회")
    public ResponseEntity<SuccessResponseDto<User>> getUserById(
            @Min(value = 1, message = "회원 ID는 1이상 정수 입니다.")
            @ApiParam(value = "회원 ID", example = "1", required = true)
            @PathVariable int userId) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.getUserById(userId)));
    }

    @GetMapping("/api/admin/user/delete/{userId}")
    @ApiOperation(value = "회원 삭제")
    public ResponseEntity<SuccessResponseDto<?>> deleteUserById(
            @Min(value = 1, message = "회원 ID는 1이상 정수 입니다.")
            @ApiParam(value = "회원 ID", example = "1", required = true)
            @PathVariable int userId) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.deleteUserById(userId)));
    }
}

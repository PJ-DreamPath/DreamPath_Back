package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.response.RespAdminUserDto;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@Api(tags = "관리자 API")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/admin/users")
    @ApiOperation(value = "회원 전체 조회")
    public ResponseEntity<List<RespAdminUserDto>> findAllUsers() throws NotFoundException {

        return ResponseEntity.ok().body(adminUserService.getAllUser());
    }

    @DeleteMapping("/admin/users/{userId}")
    @ApiOperation(value = "회원 삭제")
    public ResponseEntity<?> deleteUser(
            @Min(value = 1, message = "회원 ID는 1 이상의 정수입니다.")
            @ApiParam(value = "회원 ID 번호", example = "1", required = true)
            @PathVariable int userId) throws NotFoundException {
        return ResponseEntity.ok().body(adminUserService.deleteUser(userId));
    }
}

package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.response.RespAdminUserDto;
import com.korit.dreampath_back.dto.response.post.RespAdminPostListDto;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.service.AdminPostService;
import com.korit.dreampath_back.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
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

    @Autowired
    private AdminPostService adminPostService;

    @GetMapping("/admin/users")
    @Operation(summary = "관리자 회원 전체 조회")
    public ResponseEntity<List<RespAdminUserDto>> findAllUsers() throws NotFoundException {

        return ResponseEntity.ok().body(adminUserService.getAllUser());
    }

    @DeleteMapping("/admin/users/{userId}")
    @Operation(summary = "관리자 회원 삭제")
    public ResponseEntity<?> deleteUser(
            @Min(value = 1, message = "회원 ID는 1 이상의 정수입니다.")
            @ApiParam(value = "회원 ID 번호", example = "1", required = true)
            @PathVariable int userId) throws NotFoundException {
        return ResponseEntity.ok().body(adminUserService.deleteUser(userId));
    }

    @GetMapping("/admin/posts")
    @Operation(summary = "관리자 게시글 전체 조회")
    public ResponseEntity<List<RespAdminPostListDto>> findAllPosts() throws NotFoundException {
        return ResponseEntity.ok().body(adminPostService.getAdminPostList());
    }

    @DeleteMapping("/admin/post/{postId}")
    @Operation(summary = "관리자 게시글 삭제")
    public ResponseEntity<?> deletePost(
            @Min(value = 1, message = "게시글 ID는 1 이상의 정수입니다.")
            @ApiParam(value = "게시글 ID 번호", example = "1", required = true)
            @PathVariable int postId) throws NotFoundException {
        return ResponseEntity.ok().body(adminPostService.deletePost(postId));
    }
}

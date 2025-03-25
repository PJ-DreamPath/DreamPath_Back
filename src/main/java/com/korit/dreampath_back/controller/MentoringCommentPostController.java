package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentDeleteDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentPageDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentUpdateDto;
import com.korit.dreampath_back.dto.response.comment.RespMentoringCommentDto;
import com.korit.dreampath_back.dto.response.comment.RespMentoringCommentPageDto;
import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.MentoringCommentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MentoringCommentPostController {

    @Autowired
    private MentoringCommentService mentoringCommentService;

    @GetMapping("/comment")
    @Operation(summary = "조회")
    public ResponseEntity<RespMentoringCommentPageDto> getComments(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @ModelAttribute ReqMentoringCommentPageDto dto) {
        return ResponseEntity.ok().body(mentoringCommentService.getCommentWithPage(principalUser, dto));
    }

    @PostMapping("/comment/me")
    @Operation(summary = "멘토링 후기 등록")
    public ResponseEntity<String> addComment(
            @AuthenticationPrincipal PrincipalUser principalUser,
            ReqMentoringCommentDto commentDto) {

        boolean addReview = mentoringCommentService.addComment(principalUser.getUser(), commentDto);

        if (addReview) {
            return ResponseEntity.ok().body("완료");
        } else {
            return ResponseEntity.badRequest().body("실패");
        }

    }

        @PutMapping("/content")
        @Operation(summary = "내용 수정")
        public ResponseEntity<String> changeContent(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @ModelAttribute ReqMentoringCommentUpdateDto reqMentoringCommentUpdateDto) {

            boolean updateContentCheck = mentoringCommentService.updateComment(principalUser.getUser(), reqMentoringCommentUpdateDto);

            if (updateContentCheck) {
                return ResponseEntity.ok().body("수정 성공");
            } else {
                return ResponseEntity.ok().body("수정 실패");
            }
    }

    @DeleteMapping("/comment")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<?> deleteComment(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @ModelAttribute ReqMentoringCommentDeleteDto reqMentoringCommentDeleteDto
        ) {

       boolean deleteCommentCheck = mentoringCommentService.deleteComment(principalUser.getUser(), reqMentoringCommentDeleteDto);
       if (deleteCommentCheck) {
           return ResponseEntity.ok().body("삭제 성공");
       } else
           return ResponseEntity.ok().body("삭제 실패");
    }
}



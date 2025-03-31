package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentDeleteDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentPageDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentUpdateDto;
import com.korit.dreampath_back.dto.response.comment.RespMentoringCommentPageDto;
import com.korit.dreampath_back.entity.CommentSearch;
import com.korit.dreampath_back.entity.MentoringComment;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.entity.UserRole;
import com.korit.dreampath_back.repository.MentoringCommentRepository;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class MentoringCommentService {

    @Autowired
    private MentoringCommentRepository mentoringCommentRepository;

    @Transactional(rollbackFor = Exception.class)
    public RespMentoringCommentPageDto getCommentWithPage(ReqMentoringCommentPageDto dto, int postId) {


        int startIndex = (dto.getPage() - 1) * dto.getLimitCount();
        List<CommentSearch> commentSearchLists = mentoringCommentRepository.findCommentByPostId(startIndex, dto.getLimitCount(), postId);
        int totalComments = mentoringCommentRepository.getCountsByPostId(postId);
        int totalPages = totalComments % dto.getLimitCount() == 0
                ? totalComments / dto.getLimitCount()
                : totalComments / dto.getLimitCount() + 1;

        RespMentoringCommentPageDto respMentoringCommentPageDto =RespMentoringCommentPageDto.builder()
                .page(dto.getPage())
                .limitCount(dto.getLimitCount())
                .isFirstPage(dto.getPage() == 1)
                .isLastPage(dto.getPage() == totalPages)
                .totalPages(totalPages)
                .totalElements(totalComments)
                .nextPage(dto.getPage() == totalPages ? totalPages : dto.getPage() + 1)
                .commentSearchList(commentSearchLists)
                .build();
        return respMentoringCommentPageDto;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean addComment(User user, ReqMentoringCommentDto commentDto){

        if (commentDto.getStarPoint() < 1 || commentDto.getStarPoint() > 5) {

            return false;
        }

        MentoringComment newComment = MentoringComment.builder()
                .postId(commentDto.getPostId())
                .userId(user.getUserId())
                .starPoint(commentDto.getStarPoint())
                .content(commentDto.getContent())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();


        return mentoringCommentRepository.addComment(newComment) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateComment(PrincipalUser principalUser, ReqMentoringCommentUpdateDto updateDto) {

        MentoringComment updateMentoringComment = MentoringComment.builder()
                .commentId(updateDto.getCommentId())
                .userId(principalUser.getUser().getUserId())
                .content(updateDto.getContent())
                .starPoint(updateDto.getStarPoint())
                .build();

        return mentoringCommentRepository.updateComment(updateMentoringComment) > 0;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean deleteComment(PrincipalUser principalUser, int commentId, int userId) {

        boolean isAdmin = false;
        for(UserRole role : principalUser.getUser().getUserRoles()) {
            if( role.getRoleId() == 3) {
                isAdmin = true;
            }
        }
        if(userId != principalUser.getUser().getUserId() && !isAdmin) {
            return false;
        }
        return mentoringCommentRepository.deleteComment(commentId) > 0;
    }


}

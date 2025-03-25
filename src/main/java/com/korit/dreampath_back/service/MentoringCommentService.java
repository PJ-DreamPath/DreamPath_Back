package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentDeleteDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentPageDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentUpdateDto;
import com.korit.dreampath_back.dto.response.comment.RespMentoringCommentDto;
import com.korit.dreampath_back.dto.response.comment.RespMentoringCommentPageDto;
import com.korit.dreampath_back.entity.Comment;
import com.korit.dreampath_back.entity.Mentoring;
import com.korit.dreampath_back.entity.MentoringComment;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.repository.MentoringCommentRepository;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MentoringCommentService {

    @Autowired
    private MentoringCommentRepository mentoringCommentRepository;

    @Transactional(rollbackFor = Exception.class)
    public RespMentoringCommentPageDto getCommentWithPage(PrincipalUser principalUser, ReqMentoringCommentPageDto dto) {
        int userId = principalUser.getUser().getUserId();

        int totalComments = mentoringCommentRepository.countTotalComments();
        int startIndex = (dto.getPage() - 1) * dto.getLimitCount();
        int totalPages = totalComments % dto.getLimitCount() == 0
                ? totalComments / dto.getLimitCount()
                : totalComments / dto.getLimitCount() + 1;


        List<Mentoring> mentoringLists = mentoringCommentRepository.getCommentPageWithNickname(userId, startIndex, dto.getLimitCount(), dto.getNickName());

        RespMentoringCommentPageDto respMentoringCommentPageDto =RespMentoringCommentPageDto.builder()
                .page(dto.getPage())
                .limitCount(dto.getLimitCount())
                .isFirstPage(dto.getPage() == 1)
                .isLastPage(dto.getPage() == totalPages)
                .totalPages(totalPages)
                .nextPage(dto.getPage() == totalPages ? totalPages : dto.getPage() + 1)
                .mentoringList(mentoringLists)
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

         System.out.println(newComment);

        return mentoringCommentRepository.addComment(newComment) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateComment(User user, ReqMentoringCommentUpdateDto updateDto) {

        MentoringComment updateMentoringComment = MentoringComment.builder()
                .postId(updateDto.getPostId())
                .commentId(updateDto.getCommentId())
                .userId(user.getUserId())
                .content(updateDto.getContent())
                .starPoint(updateDto.getStarPoint())
                .build();

        return mentoringCommentRepository.updateComment(updateMentoringComment) > 0;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean deleteComment(User user, ReqMentoringCommentDeleteDto deleteDto) {

        MentoringComment deleteMentoringComment = MentoringComment.builder()
                .commentId(deleteDto.getCommentId())
                .userId(user.getUserId())
                .postId(deleteDto.getPostId())
                .build();

        MentoringComment dbComment =  mentoringCommentRepository.findCommentPostUser(
                deleteMentoringComment.getCommentId(),
                deleteMentoringComment.getUserId(),
                deleteMentoringComment.getPostId());


        if (dbComment != null &&
            dbComment.getCommentId() == deleteDto.getCommentId() &&
            dbComment.getUserId() == user.getUserId() &&
            dbComment.getPostId() == deleteDto.getPostId()) {
            System.out.println("삭제 성공");

                return mentoringCommentRepository.deleteComment(deleteMentoringComment) > 0;

            }
            System.out.println("삭제 실패");
            return false;
    }


}

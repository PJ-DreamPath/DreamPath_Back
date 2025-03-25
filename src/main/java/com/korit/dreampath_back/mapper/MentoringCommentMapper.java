package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentUpdateDto;
import com.korit.dreampath_back.dto.response.comment.RespMentoringCommentDto;
import com.korit.dreampath_back.entity.Mentoring;
import com.korit.dreampath_back.entity.MentoringComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MentoringCommentMapper {

    // 조회(페이지 포함)
    List<Mentoring> getCommentPageWithNickname(
            int userId,
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("nickname") String nickName
    );

    int countTotalComments();

    // 후기 등록
    int createComment(MentoringComment mentoringComment);

    int updateComment(MentoringComment mentoringComment
    );


   MentoringComment findByComment(
            @Param("commentId") int commentId,
            @Param("userId") int userId,
            @Param("postId") int postId
    );
    int deleteComment(MentoringComment mentoringComment);

}

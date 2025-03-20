package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MentoringCommentMapper {

    // 후기 등록
    int createComment(Comment comment);

    // 후기 수정
    int updateComment(Comment comment);

    // 후기 삭제
    int deleteComment(int commentId);
}

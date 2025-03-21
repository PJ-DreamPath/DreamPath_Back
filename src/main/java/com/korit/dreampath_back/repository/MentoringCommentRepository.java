package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.Comment;
import com.korit.dreampath_back.mapper.MentoringCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MentoringCommentRepository {

    @Autowired
    private MentoringCommentMapper mentoringCommentMapper;

    public int addComment(Comment comment) {
        return mentoringCommentMapper.createComment(comment);
    }

    public int updateComment(Comment comment) {
        return mentoringCommentMapper.updateComment(comment);
    }

    public int deleteComment(int commentId) {
        mentoringCommentMapper.deleteComment(commentId);

        return commentId;
    }

}

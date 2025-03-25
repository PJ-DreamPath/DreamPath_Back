package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.Mentoring;
import com.korit.dreampath_back.entity.MentoringComment;
import com.korit.dreampath_back.mapper.MentoringCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MentoringCommentRepository {

    @Autowired
    private MentoringCommentMapper mentoringCommentMapper;

    // 댓글 조회
    public List<Mentoring> getCommentPageWithNickname(int userId, int startIndex, int limitCount, String nickName) {
        return mentoringCommentMapper.getCommentPageWithNickname(userId, startIndex, limitCount, nickName );
    }

    // 전체 댓글 수
    public int countTotalComments() {
        return mentoringCommentMapper.countTotalComments();
    }

    public int addComment(MentoringComment mentoringComment) {

        return mentoringCommentMapper.createComment(mentoringComment);
    }

    // 수정
    public int updateComment(MentoringComment mentoringComment) {

        return mentoringCommentMapper.updateComment(mentoringComment); }

    public MentoringComment findCommentPostUser(int commentId, int userId, int postId) {
        return mentoringCommentMapper.findByComment(commentId, userId, postId);
    }

    // 삭제
    public int deleteComment(MentoringComment mentoringComment) { return mentoringCommentMapper.deleteComment(mentoringComment); }
}

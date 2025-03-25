package com.korit.dreampath_back.dto.request.comment;

import lombok.Data;

@Data
public class ReqMentoringCommentUpdateDto {

    private int commentId;
    private int postId;
    private int userId;
    private String  content;
    private int starPoint;

}

package com.korit.dreampath_back.dto.request.comment;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ReqMentoringCommentDto {

    private int commentId;
    private int postId;

    private String content;
    private int starPoint;



}

package com.korit.dreampath_back.dto.request.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqMentoringCommentPageDto {

    private int page;
    private int limitCount;
}

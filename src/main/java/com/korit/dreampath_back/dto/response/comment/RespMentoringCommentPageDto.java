package com.korit.dreampath_back.dto.response.comment;

import com.korit.dreampath_back.entity.Mentoring;
import com.korit.dreampath_back.entity.MentoringComment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespMentoringCommentPageDto {

    private int page;
    private int limitCount;
    private boolean isFirstPage; // 첫 페이지
    private boolean isLastPage; // 마지막 페이지
    private int totalPages; // 전체 페이지
    private int nextPage; // 다음 페이지



    private List<Mentoring> mentoringList;

}

package com.korit.dreampath_back.dto.response;

import com.korit.dreampath_back.entity.MyMentoringSearch;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespMyMentoringList {
    private int page;
    private int limitCount;
    private int totalPages;
    private int totalElements;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int nextPage;
    private List<MyMentoringSearch> myMentoringSearchList;
}

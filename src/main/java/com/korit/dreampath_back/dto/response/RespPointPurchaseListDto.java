package com.korit.dreampath_back.dto.response;

import com.korit.dreampath_back.entity.PointPurchaseSearch;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class RespPointPurchaseListDto {
    private int page;
    private int limitCount;
    private int totalPages;
    private int totalElements;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int nextPage;
    private List<PointPurchaseSearch> pointPurchaseSearchList;
}

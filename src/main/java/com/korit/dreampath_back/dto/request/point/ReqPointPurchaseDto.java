package com.korit.dreampath_back.dto.request.point;

import lombok.Data;

@Data
public class ReqPointPurchaseDto {
    private int page;
    private int limitCount;
    private String order;
    private String searchText;
}

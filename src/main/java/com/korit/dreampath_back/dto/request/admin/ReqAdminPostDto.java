package com.korit.dreampath_back.dto.request.admin;

import lombok.Data;

@Data
public class ReqAdminPostDto {
    private int page;
    private int limitCount;
    private String order;
    private String searchText;
}

package com.korit.dreampath_back.dto.response.admin;

import com.korit.dreampath_back.entity.PostAdmin;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.entity.UserAdmin;
import com.korit.dreampath_back.entity.UserSearch;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespAdminPostListDto {

    private int page;
    private int limitCount;
    private int totalPages;
    private int totalElements;
    private boolean isFirstPage;
    private boolean isLastPage;

    private int nextPage;
    private List<PostAdmin> postList;
}

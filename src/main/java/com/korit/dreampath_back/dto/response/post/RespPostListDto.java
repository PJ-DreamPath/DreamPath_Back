package com.korit.dreampath_back.dto.response.post;

import com.korit.dreampath_back.entity.PostListPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "게시글 조회 응답 DTO")
public class RespPostListDto {
    private int page;
    private int limitCount;
    private int totalPages;
    private int totalElements;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int nextPage;
    private List<PostListPage> postList;
}

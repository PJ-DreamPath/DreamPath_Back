package com.korit.dreampath_back.dto.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "게시글 검색 요청 DTO")
public class ReqPostSearchDto {
    @Schema(description = "페이징")
    private int page;
    @Schema(description = "게시글 갯수")
    private int limitCount;
    @Schema(description = "게시글 정렬")
    private String order;
    @Schema(description = "게시글 검색 텍스트")
    private String searchTxt;

}

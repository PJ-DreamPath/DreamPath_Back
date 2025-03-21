package com.korit.dreampath_back.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "멘토링 후기 작성 DTO")
public class ReqMentoringCommentDto {

    @Schema(description = "닉네임")
    private int userId;
    @Schema(description = "평점")
    private String StarPoint;
    @Schema(description = "내용")
    private String content;
    @Schema(description = "작성일")
    private LocalDateTime updateAt;

}

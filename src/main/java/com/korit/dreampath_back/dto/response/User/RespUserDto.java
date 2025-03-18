package com.korit.dreampath_back.dto.response.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "사용자 조회 수정 DTO")
public class RespUserDto {

    @Schema(description = "사용자 ID", example = "1")
    private int userId;

    @Schema(description = "사용자명", example = "user12")
    private String username;

    @Schema(description = "닉네임", example = "nickname")
    private String nickname;

    @Schema(description = "이메일", example = "user12@naver.com")
    private String email;

    @Schema(description = "프로필 이미지", example = "profile.png")
    private String profileImg;

}

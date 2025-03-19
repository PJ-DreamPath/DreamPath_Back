package com.korit.dreampath_back.dto.request.User;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "사용자 조회 수정 DTO")
public class ReqUserUpdateDto {


    @Schema(description = "사용자 ID", example = "1")
    private int userId;
    @Schema(description = "비밀번호 수정", example = "newPass11")
    private String password;
    @Schema(description = "닉네임 수정", example = "newNick")
    private String nickname;
    @Schema(description = "이메일 수정", example = "newEmail@naver.com")
    private String email;


}

package com.korit.dreampath_back.dto.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespAdminPostListDto {
    private int postId;
    private String title;
    private String nickname;
    private String boardName;
    private LocalDateTime createdAt;
    private int commentCount;
    private int viewCount;
}

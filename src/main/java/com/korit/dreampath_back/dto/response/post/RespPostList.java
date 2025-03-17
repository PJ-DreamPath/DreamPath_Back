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
public class RespPostList {

    private int postId;
    private int boardId;
    private int userId;
    private String title;
    private int starPoint;

    private LocalDateTime createdAt;

    private int viewCount;
}

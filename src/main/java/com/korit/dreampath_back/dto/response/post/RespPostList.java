package com.korit.dreampath_back.dto.response.post;


import com.korit.dreampath_back.entity.User;
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
    private String content;

    private String status;
    private LocalDateTime createdAt;

    private int viewCount;

    private User user;
}

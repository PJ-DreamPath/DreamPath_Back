package com.korit.dreampath_back.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDetail {

    private int postId;
    private int boardId;
    private int userId;
    private int categoryId;
    private String categoryName;
    private String title;
    private String content;
    private int starPoint;
    private String mentoringAddress;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int viewCount;
    private String attachedFiles;

    private int likeCount;

    private User user;
}

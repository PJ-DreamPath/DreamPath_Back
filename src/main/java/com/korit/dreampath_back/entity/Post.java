package com.korit.dreampath_back.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private int postId;
    private int boardId;
    private int userId;
    private int mentoringCategoryId;
    private String title;
    private String content;
    private int starPoint;
    private String mentoringAddress;
    private Date startDate;
    private Date endDate;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int viewCount;
    private String attachedFiles;
}

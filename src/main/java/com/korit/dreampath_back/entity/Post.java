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
    private int boardCategoryId;
    private String title;
    private String content;
    private int starPoint;
    private String mentoringAddress;
    private Date startDate;
    private Date endDate;
    private int status;
    private int viewCount;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

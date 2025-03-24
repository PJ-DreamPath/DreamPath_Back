package com.korit.dreampath_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyMentoringSearch {
    private String status;
    private String title;
    private String createdAt;
    private int commentCount;
    private int likeCount;
    private int viewCount;
    private int postId;
}

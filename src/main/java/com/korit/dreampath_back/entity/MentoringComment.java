package com.korit.dreampath_back.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentoringComment {

    private int commentId;
    private int postId;
    private int userId;
    private String content;

    private String starPoint;

    private LocalDateTime updateAt;
    private LocalDateTime createdAt;

}

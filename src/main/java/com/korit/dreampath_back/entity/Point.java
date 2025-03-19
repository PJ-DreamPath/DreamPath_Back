package com.korit.dreampath_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    private int pointId;
    private String pointName;
    private String pointPrice;

    private LocalDateTime createAt;

}

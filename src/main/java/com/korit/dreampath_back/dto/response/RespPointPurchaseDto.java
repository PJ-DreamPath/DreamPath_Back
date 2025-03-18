package com.korit.dreampath_back.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class RespPointPurchaseDto {
    String pointName;
    int pointPrice;
    LocalDateTime createdAt;
}

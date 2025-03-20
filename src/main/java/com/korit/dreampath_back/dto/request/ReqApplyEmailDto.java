package com.korit.dreampath_back.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqApplyEmailDto {
    private int postId;
    private String email;
}

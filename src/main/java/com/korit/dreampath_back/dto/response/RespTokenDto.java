package com.korit.dreampath_back.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespTokenDto {

    private String type;
    private String name;
    private String token;

}

package com.korit.dreampath_back.dto.response;

import lombok.Builder;
import lombok.Data;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

@Builder
@Data
public class RespAuthPhoneDto {
    private String code;
}

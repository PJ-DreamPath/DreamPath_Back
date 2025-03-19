package com.korit.dreampath_back.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespTicketPurchaseDto {

    private int ticketId;
    private String ticketName;
    private int price;
    private LocalDateTime createAt;
}

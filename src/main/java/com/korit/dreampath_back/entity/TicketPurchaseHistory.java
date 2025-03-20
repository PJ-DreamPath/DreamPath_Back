package com.korit.dreampath_back.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketPurchaseHistory {

    private LocalDateTime createdAt;

    private String ticketName;
    private int price;

}

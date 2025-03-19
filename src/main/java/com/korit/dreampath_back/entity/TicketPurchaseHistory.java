package com.korit.dreampath_back.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketPurchaseHistory {

    String ticketName;
    int entryCount;
    int price;

    LocalDateTime createdAt;
}

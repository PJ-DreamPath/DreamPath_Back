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
public class TicketPurchase {

    private int pointPurchaseId;
    private int ticketId;
    private int userId;
    private String ticketName;


    private int price;
    private int entryCount;

    private Ticket ticket;
    private User user;

    private LocalDateTime createdAt;
}

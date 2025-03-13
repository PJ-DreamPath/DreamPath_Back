package com.korit.dreampath_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private int tickerId;
    private String ticketName;
    private int entryCount;
    private int price;

    private LocalDateTime createAt;
}

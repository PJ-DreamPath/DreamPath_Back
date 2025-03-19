package com.korit.dreampath_back.service;

import com.korit.dreampath_back.entity.TicketPurchase;
import com.korit.dreampath_back.entity.TicketPurchaseHistory;
import com.korit.dreampath_back.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketPurchaseHistory> getTicketPurchase(int userId) {
        List<TicketPurchase> ticketPurchaseList = ticketRepository.getTicketPurchase(userId);
        List<TicketPurchaseHistory> ticketPurchaseHistories = new ArrayList<>();
        for (TicketPurchase ticketPurchase : ticketPurchaseList) {
            TicketPurchaseHistory ticketPurchaseHistory = TicketPurchaseHistory.builder()
                    .ticketName(ticketPurchase.getTicket().getTicketName())
                    .entryCount(ticketPurchase.getEntryCount())
                    .price(ticketPurchase.getTicket().getPrice())
                    .createdAt(ticketPurchase.getCreatedAt())
                    .build();
            ticketPurchaseHistories.add(ticketPurchaseHistory);
        }
        return ticketPurchaseHistories;
    }

}

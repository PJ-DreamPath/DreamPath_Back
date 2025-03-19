package com.korit.dreampath_back.service;

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

    public List<TicketPurchaseHistory> findAllByUserId(int userId) {
        return ticketRepository.findAllByUserId(userId);

    }

}

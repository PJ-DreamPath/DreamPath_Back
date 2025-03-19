package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.Ticket;
import com.korit.dreampath_back.entity.TicketPurchase;
import com.korit.dreampath_back.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository {

    @Autowired
    private TicketMapper ticketMapper;

    public List<TicketPurchase> getTicketPurchase(int userId) {return ticketMapper.getTicketPurchase(userId);}

}


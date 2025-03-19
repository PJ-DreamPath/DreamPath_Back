package com.korit.dreampath_back.controller;


import com.korit.dreampath_back.dto.response.RespTicketPurchaseDto;
import com.korit.dreampath_back.entity.Ticket;
import com.korit.dreampath_back.entity.TicketPurchase;
import com.korit.dreampath_back.entity.TicketPurchaseHistory;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.TicketService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket")
    public ResponseEntity<List<TicketPurchaseHistory>> getTicketPurchase(
            @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUser().getUserId();

        return ResponseEntity.ok().body(ticketService.getTicketPurchase(userId));
    }
}



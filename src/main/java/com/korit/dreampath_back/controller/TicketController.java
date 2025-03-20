package com.korit.dreampath_back.controller;


import com.korit.dreampath_back.dto.request.ReqTicketPurchaseDto;
import com.korit.dreampath_back.dto.response.RespTicketPurchaseListDto;
import com.korit.dreampath_back.entity.TicketPurchaseHistory;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/history")
    public ResponseEntity<RespTicketPurchaseListDto> findAllByUserId(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @ModelAttribute ReqTicketPurchaseDto dto
    ) {

       return ResponseEntity.ok().body(ticketService.getPointPurchase(principalUser, dto));
    }

}



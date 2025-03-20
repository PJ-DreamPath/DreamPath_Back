package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.ReqTicketPurchaseDto;
import com.korit.dreampath_back.dto.response.RespTicketPurchaseListDto;
import com.korit.dreampath_back.entity.PointPurchase;
import com.korit.dreampath_back.entity.PointPurchaseSearch;
import com.korit.dreampath_back.entity.TicketPurchaseHistory;
import com.korit.dreampath_back.repository.TicketRepository;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public RespTicketPurchaseListDto getPointPurchase(PrincipalUser principalUser, ReqTicketPurchaseDto dto) {
        int userId = principalUser.getUser().getUserId();

        int totalTicketPurchaseListCount = ticketRepository.findAllTicketPurchaseCount(userId);
        int totalPages = totalTicketPurchaseListCount % dto.getLimitCount() == 0
                ? totalTicketPurchaseListCount / dto.getLimitCount()
                : totalTicketPurchaseListCount / dto.getLimitCount() + 1;

        int startIndex = (dto.getPage() - 1 ) * dto.getLimitCount();
        List<TicketPurchaseHistory> ticketPurchaseHistories = ticketRepository.findAllByUserId(userId,startIndex,dto.getLimitCount(), dto.getOrder());
        RespTicketPurchaseListDto respTicketPurchaseListDto = RespTicketPurchaseListDto.builder()
                .page(dto.getPage())
                .limitCount(dto.getLimitCount())
                .isFirstPage(dto.getPage()== 1)
                .isLastPage(dto.getPage() == totalPages)
                .totalPages(totalPages)
                .totalElements(totalTicketPurchaseListCount)
                .nextPage(dto.getPage() == totalPages ? totalPages : dto.getPage() + 1)
                .ticketPurchaseHistoryList(ticketPurchaseHistories)
                .build();
        return respTicketPurchaseListDto;
    }


}

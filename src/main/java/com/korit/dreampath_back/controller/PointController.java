package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.point.ReqPointPurchaseDto;
import com.korit.dreampath_back.dto.response.RespPointPurchaseListDto;
import com.korit.dreampath_back.entity.PointPurchaseSearch;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/point")
public class PointController {
    @Autowired
    private PointService pointService;
    @GetMapping("/purchase")
    public ResponseEntity<?> getPointPurchase(@ModelAttribute ReqPointPurchaseDto dto, @AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUser().getUserId();

        int totalPointPurchaseListCount = pointService.findAllPointPurchase(userId);
        int totalPages = totalPointPurchaseListCount % dto.getLimitCount() == 0
                ? totalPointPurchaseListCount / dto.getLimitCount()
                : totalPointPurchaseListCount / dto.getLimitCount() + 1;
        System.out.println(pointService.getPointPurchase(userId, dto));
        RespPointPurchaseListDto pointPurchaseListDto = RespPointPurchaseListDto.builder()
                .page(dto.getPage())
                .limitCount(dto.getLimitCount())
                .totalPages(totalPages)
                .totalElements(totalPointPurchaseListCount)
                .isFirstPage(dto.getPage() == 1)
                .isLastPage(dto.getPage() == totalPages)
                .nextPage(dto.getPage() != totalPages ? dto.getPage() + 1 : dto.getPage())
                .pointPurchaseSearchList(pointService.getPointPurchase(userId, dto))
                .build();

        return ResponseEntity.ok().body(pointPurchaseListDto);
    }




}

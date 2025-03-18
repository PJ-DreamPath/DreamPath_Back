package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.response.RespPointPurchaseDto;
import com.korit.dreampath_back.entity.PointPurchase;
import com.korit.dreampath_back.entity.PointPurchaseSearch;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PointController {
    @Autowired
    private PointService pointService;
    @GetMapping("/point")
    public ResponseEntity<List<PointPurchaseSearch>> getPointPurchase(@AuthenticationPrincipal PrincipalUser principalUser) {
        int userId = principalUser.getUser().getUserId();

        return ResponseEntity.ok().body(pointService.getPointPurchase(userId));
    }
}

package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.entity.MentoringCategory;
import com.korit.dreampath_back.service.MentoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mentoring")
public class MentoringController {

    @Autowired
    private MentoringService mentoringService;

    @GetMapping("/categories")
    @Operation(summary = "멘토링 카테고리 전체 조회")
    public ResponseEntity<List<MentoringCategory>> getCategory() {
        return ResponseEntity.ok().body(mentoringService.findAllCategories());
    }

}

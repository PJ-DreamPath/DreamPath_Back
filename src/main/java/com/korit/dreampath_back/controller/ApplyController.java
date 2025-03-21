package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.ReqApplyEmailDto;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.ApplyService;
import com.korit.dreampath_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentoring")
public class ApplyController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplyService applyService;

    @PostMapping("/apply")
    public ResponseEntity<?> sendApplyMail(@AuthenticationPrincipal PrincipalUser principalUser, @RequestBody ReqApplyEmailDto reqApplyEmailDto) throws Exception {

        applyService.sendApplyMail(reqApplyEmailDto, principalUser);
        return ResponseEntity.ok().build();
    }

}

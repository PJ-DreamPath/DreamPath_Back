package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.post.ReqPostSearchDto;
import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.service.PostService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/list/{boardId}")
    public List<Post> getPostList(
            @PathVariable int boardId,
            @ModelAttribute ReqPostSearchDto searchDto
    ) throws NotFoundException {
        return postService.getPostList(boardId, searchDto);
    }
}

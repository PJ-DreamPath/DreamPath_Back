package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.post.ReqPostCreateDto;
import com.korit.dreampath_back.dto.request.post.ReqPostSearchDto;
import com.korit.dreampath_back.dto.response.post.RespPostDto;
import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.service.PostService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<String> addPost(@RequestBody ReqPostCreateDto createDto) {
        System.out.println(createDto);
        return postService.addPost(createDto)
                ? ResponseEntity.ok().body("등록완료")
                : ResponseEntity.badRequest().body("등록실패");
    }

    @GetMapping("/list/{boardId}")
    public ResponseEntity<RespPostDto> getPostList(
            @PathVariable int boardId,
            @ModelAttribute ReqPostSearchDto searchDto
    ) throws NotFoundException {

        int totalBoardListCount = postService.getPostListCountAllBySearchTxt(searchDto.getSearchTxt());
        int totalPages = totalBoardListCount % searchDto.getLimitCount() == 0
                ? totalBoardListCount / searchDto.getLimitCount()
                : totalBoardListCount / searchDto.getLimitCount() + 1;

        RespPostDto newRespDto = RespPostDto.builder()
                .page(searchDto.getPage())
                .limitCount(searchDto.getLimitCount())
                .totalPages(totalPages)
                .totalElements(totalBoardListCount)
                .isFirstPage(searchDto.getPage() == 1)
                .isLastPage(searchDto.getPage() == totalPages)
                .nextPage(searchDto.getPage() != totalPages ? searchDto.getPage() + 1 : 0)
                .postList(postService.getPostList(boardId, searchDto))
                .build();
        return ResponseEntity.ok().body(newRespDto);

    }
}

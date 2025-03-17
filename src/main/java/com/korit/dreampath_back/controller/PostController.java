package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.dto.request.post.ReqPostCreateDto;
import com.korit.dreampath_back.dto.request.post.ReqPostSearchDto;
import com.korit.dreampath_back.dto.response.post.RespPostListDto;
import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import com.korit.dreampath_back.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    @Operation(summary = "게시글 등록")
    public ResponseEntity<String> addPost(@AuthenticationPrincipal PrincipalUser principalUser,
                                          @RequestBody ReqPostCreateDto createDto) {

        return postService.addPost(principalUser.getUser(), createDto)
                ? ResponseEntity.ok().body("등록완료")
                : ResponseEntity.badRequest().body("등록실패");
    }

    @GetMapping("/list/{boardId}")
    @Operation(summary = "게시글 전체, 다건 조회")
    public ResponseEntity<RespPostListDto> getPostList(
            @PathVariable int boardId,
            @ModelAttribute ReqPostSearchDto searchDto
    ) throws NotFoundException {

        int totalPostListCount = postService.getPostListCountAllBySearchTxt(searchDto.getSearchTxt());
        int totalPages = totalPostListCount % searchDto.getLimitCount() == 0
                ? totalPostListCount / searchDto.getLimitCount()
                : totalPostListCount / searchDto.getLimitCount() + 1;

        RespPostListDto newRespDto = RespPostListDto.builder()
                .page(searchDto.getPage())
                .limitCount(searchDto.getLimitCount())
                .totalPages(totalPages)
                .totalElements(totalPostListCount)
                .isFirstPage(searchDto.getPage() == 1)
                .isLastPage(searchDto.getPage() == totalPages)
                .nextPage(searchDto.getPage() != totalPages ? searchDto.getPage() + 1 : 0)
                .postList(postService.getPostList(boardId, searchDto))
                .build();
        return ResponseEntity.ok().body(newRespDto);

    }

    @GetMapping("/{postId}")
    @Operation(summary = "게시글 상세 조회")
    public ResponseEntity<Post> getPostDetail(
            @PathVariable int postId
    ) {
        return postService.getPostDetail(postId).isPresent()
                ? ResponseEntity.ok().body(postService.getPostDetail(postId).get())
                : ResponseEntity.badRequest().body(postService.getPostDetail(postId).get()) ;
    }
}

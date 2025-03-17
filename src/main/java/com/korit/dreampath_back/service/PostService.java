package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.post.ReqPostCreateDto;
import com.korit.dreampath_back.dto.request.post.ReqPostSearchDto;
import com.korit.dreampath_back.dto.response.post.RespPostDto;
import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.repository.PostRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public int getPostListCountAllBySearchTxt(String searchTxt) {
        return postRepository.findPostListCountAllBySearchTxt(searchTxt);
    }
    public boolean addPost(ReqPostCreateDto createDto) {
        Post newPost = Post.builder()
                .boardId(createDto.getBoardId())
                .userId(createDto.getUserId())
                .mentoringCategoryId(createDto.getMentoringCategoryId())
                .title(createDto.getTitle())
                .content(createDto.getContent())
                .mentoringAddress(createDto.getMentoringAddress())
                .startDate(createDto.getStartDate())
                .endDate(createDto.getEndDate())
                .status(createDto.getStatus())
                .attachedFiles(createDto.getAttachedFiles())
                .boardId(createDto.getBoardId())
                .build();

        System.out.println(newPost);
        return postRepository.addPost(newPost) > 0 ? true : false;
    }

    public List<Post> getPostList(int boardId, ReqPostSearchDto searchDto) throws NotFoundException {
        int startIdx = (searchDto.getPage() - 1) * searchDto.getLimitCount();

        return postRepository.findPostList(boardId, startIdx, searchDto.getLimitCount(), searchDto.getOrder(), searchDto.getSearchTxt())
                .orElseThrow(() -> new NotFoundException("검색된 게시글이 없습니다."));
    }
}

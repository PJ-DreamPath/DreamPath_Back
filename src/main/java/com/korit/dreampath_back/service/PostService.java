package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.post.ReqPostCreateDto;
import com.korit.dreampath_back.dto.request.post.ReqPostLikeDto;
import com.korit.dreampath_back.dto.request.post.ReqPostSearchDto;
import com.korit.dreampath_back.dto.request.post.ReqPostUpdateDto;
import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.dto.response.post.RespPostList;
import com.korit.dreampath_back.entity.PostLike;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.mapper.PostLikeMapper;
import com.korit.dreampath_back.repository.PostLikeRepository;
import com.korit.dreampath_back.repository.PostRepository;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    public int getPostListCountAllBySearchTxt(String searchTxt) {
        return postRepository.findPostListCountAllBySearchTxt(searchTxt);
    }
    public boolean addPost(User user, ReqPostCreateDto createDto) {
        Post newPost = Post.builder()
                .boardId(createDto.getBoardId())
                .userId(user.getUserId())
                .mentoringCategoryId(createDto.getMentoringCategoryId())
                .title(createDto.getTitle())
                .content(createDto.getContent())
                .mentoringAddress(createDto.getMentoringAddress())
                .startDate(createDto.getStartDate())
                .endDate(createDto.getEndDate())
                .status(createDto.getStatus())
                .attachedFiles(createDto.getAttachedFiles())
                .build();

        return postRepository.addPost(newPost) > 0 ? true : false;
    }

    public List<RespPostList> getPostList(int boardId, ReqPostSearchDto searchDto) throws NotFoundException {
        int startIdx = (searchDto.getPage() - 1) * searchDto.getLimitCount();

        return postRepository.findPostList(boardId, startIdx, searchDto.getLimitCount(), searchDto.getOrder(), searchDto.getSearchTxt())
                .orElseThrow(() -> new NotFoundException("검색된 게시글이 없습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public Post getPostDetail(int postId) throws NotFoundException {
        postRepository.updatePostViewCount(postId);
        return postRepository.findPostDetail(postId).orElseThrow(() -> new NotFoundException("잘못된 postId 입니다."));
    }

    public boolean updatedPost(User user, ReqPostUpdateDto updateDto) {
        Post newPost = Post.builder()
                .postId(updateDto.getPostId())
                .userId(user.getUserId())
                .mentoringCategoryId(updateDto.getMentoringCategoryId())
                .title(updateDto.getTitle())
                .content(updateDto.getContent())
                .mentoringAddress(updateDto.getMentoringAddress())
                .startDate(updateDto.getStartDate())
                .endDate(updateDto.getEndDate())
                .attachedFiles(updateDto.getAttachedFiles())
                .build();

        return postRepository.updatedPost(newPost) > 0 ? true : false;
    }

    public boolean deletePost(int postId) {
        return postRepository.deletePost(postId) > 0 ? true : false;
    }


    public void updatePostViewCount(int postId) {
        postRepository.updatePostViewCount(postId);
    }

    public boolean addPostLike(User user, ReqPostLikeDto likeDto) {
        if(postLikeRepository.findPostLikeUserByUserId(user.getUserId(), likeDto.getPostId()).isPresent()) {
            return false;
        }
        return postLikeRepository.addPostLike(user.getUserId(), likeDto.getPostId()) > 0 ? true : false;
    }
    public boolean deletePostLike(User user, ReqPostLikeDto likeDto) {
        if(postLikeRepository.findPostLikeUserByUserId(user.getUserId(), likeDto.getPostId()).isEmpty()) {
            return false;
        }
        return postLikeRepository.deletePostLike(user.getUserId(), likeDto.getPostId()) > 0 ? true : false;
    }
    public List<PostLike> findPostMyLike(User user, ReqPostLikeDto likeDto) {
        if(postLikeRepository.findPostLikeUserByUserId(user.getUserId(), likeDto.getPostId()).isEmpty()) {
            return new ArrayList<>();
        }
        return postLikeRepository.findPostLikeUserByUserId(user.getUserId(), likeDto.getPostId()).get();
    }

}

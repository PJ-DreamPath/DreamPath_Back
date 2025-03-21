package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.post.ReqPostCreateDto;
import com.korit.dreampath_back.dto.request.post.ReqPostLikeDto;
import com.korit.dreampath_back.dto.request.post.ReqPostSearchDto;
import com.korit.dreampath_back.dto.request.post.ReqPostUpdateDto;
import com.korit.dreampath_back.entity.*;
import com.korit.dreampath_back.repository.BoardRepository;
import com.korit.dreampath_back.repository.PostLikeRepository;
import com.korit.dreampath_back.repository.PostRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private FileService fileService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private BoardRepository boardRepository;

    public int getPostListCountAllBySearchTxt(int boardId, String searchTxt) {
        System.out.println(boardId);
        return postRepository.findPostListCountAllBySearchTxt(boardId, searchTxt);
    }
    public boolean addPost(User user, ReqPostCreateDto createDto) {

        final String PROFILE_IMG_FILE_PATH = "/upload/user/post";
        String saveFilename = fileService.saveFile(PROFILE_IMG_FILE_PATH, createDto.getFile()); // 폴더에 저정

        LocalDate today = LocalDate.now();


        Post newPost = Post.builder()
                .boardId(createDto.getBoardId())
                .userId(user.getUserId())
                .categoryId(createDto.getCategoryId())
                .title(createDto.getTitle())
                .content(createDto.getContent())
                .mentoringAddress(createDto.getMentoringAddress())
                .startDate(createDto.getStartDate())
                .endDate(createDto.getEndDate())
                .status(createDto.getStartDate().isBefore(today) && createDto.getEndDate().isAfter(today) ? "recruiting" : "closedRecruitment")
                .attachedFiles(saveFilename)
                .build();

        return postRepository.addPost(newPost) > 0 ? true : false;
    }

    public List<PostListPage> getPostList(int boardId, ReqPostSearchDto searchDto) throws NotFoundException {

//        int boardId = boardRepository.findBoardIdByBoardName(boardName).getBoardId();

        int startIdx = (searchDto.getPage() - 1) * searchDto.getLimitCount();

        return postRepository.findPostList(boardId, startIdx, searchDto.getLimitCount(), searchDto.getOrder(), searchDto.getSearchTxt())
                .orElseThrow(() -> new NotFoundException("검색된 게시글이 없습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public PostDetail getPostDetail(int postId) throws NotFoundException {
//        조회수 올리기
        postRepository.updatePostViewCount(postId);
        return postRepository.findPostDetail(postId).orElseThrow(() -> new NotFoundException("잘못된 postId 입니다."));
    }

    public boolean updatedPost(User user, ReqPostUpdateDto updateDto) {


        final String PROFILE_IMG_FILE_PATH = "/upload/user/post";
        String saveFilename = fileService.saveFile(PROFILE_IMG_FILE_PATH, updateDto.getFile()); // 폴더에 저정

        LocalDate today = LocalDate.now();


        Post newPost = Post.builder()
                .postId(updateDto.getPostId())
                .userId(user.getUserId())
                .categoryId(updateDto.getCategoryId())
                .title(updateDto.getTitle())
                .content(updateDto.getContent())
                .mentoringAddress(updateDto.getMentoringAddress())
                .startDate(updateDto.getStartDate())
                .endDate(updateDto.getEndDate())
                .status(updateDto.getStartDate().isBefore(today) && updateDto.getEndDate().isAfter(today) ? "recruiting" : "closedRecruitment")
                .attachedFiles(saveFilename)
                .build();

        return postRepository.updatedPost(newPost) > 0 ? true : false;
    }

    public boolean deletePost(int postId) {
        return postRepository.deletePost(postId) > 0 ? true : false;
    }


//    public void updatePostViewCount(int postId) {
//        postRepository.updatePostViewCount(postId);
//    }

    public boolean addPostLike(User user, int postId) {
        return postLikeRepository.addPostLike(user.getUserId(), postId) > 0 ? true : false;
    }
    public boolean deletePostLike(User user, int postId) {
        return postLikeRepository.deletePostLike(user.getUserId(), postId) > 0 ? true : false;
    }
    public PostLike findPostMyLike(User user, int postId) {
        return postLikeRepository.findPostLikeUserByUserId(user.getUserId(), postId);
    }

    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }

    public String updatePostStatus(int postId) {
        String status = "";
        if(postRepository.isRecruited(postId)){
            postRepository.updatePostStatusRecruiting(postId);
            status = "recruiting";
        } else {
            postRepository.updatePostStatusClosedRecruiting(postId);
            status = "closedRecruitment";
        }
        return status;
    }

}

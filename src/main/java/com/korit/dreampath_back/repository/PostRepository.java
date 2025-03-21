package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.entity.PostDetail;
import com.korit.dreampath_back.entity.PostListPage;
import com.korit.dreampath_back.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @Autowired
    private PostMapper postMapper;

//    searchTxt로 찾은 postList count
    public int findPostListCountAllBySearchTxt(int boardId, String searchTxt) {
        return postMapper.selectPostListCountAllBySearchTxt(boardId, searchTxt);
    }

//    게시글 등록
    public int addPost(Post post) {
        return postMapper.createPost(post);
    }


//    전체 조회 (검색 조회 포함)
    public Optional<List<PostListPage>> findPostList(int boardId, int startIdx, int limitCount, String order, String searchTxt) {
        return Optional.ofNullable(postMapper.selectPostList(boardId, startIdx, limitCount, order, searchTxt));
    }

//    게시글 상세 조회
    public Optional<PostDetail> findPostDetail(int postId) {
        return Optional.ofNullable(postMapper.selectPostDetail(postId));
    }

//    게시글 수정
    public int updatedPost(Post post) {
        return postMapper.updatedPost(post);
    }

//    게시글 삭제
    public int deletePost(int postId) {
        return postMapper.deletePost(postId);
    }

//    게시글 조회수
    public void updatePostViewCount(int postId) {
        postMapper.updatePostViewCount(postId);
    }

//    게시글 상태 수정
    public int updatePostStatusClosedRecruiting(int postId) {
        return postMapper.updatePostStatusClosedRecruiting(postId);
    }

    public int updatePostStatusRecruiting(int postId) {
        return postMapper.updatePostStatusRecruiting(postId);
    }

    public boolean isRecruited(int postId) {
        System.out.println(postMapper.getPostStatusByPostId(postId));
        return postMapper.getPostStatusByPostId(postId).equals("closedRecruitment");
    }
}




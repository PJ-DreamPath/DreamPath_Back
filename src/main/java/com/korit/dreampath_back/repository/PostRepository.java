package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @Autowired
    private PostMapper postMapper;

    public int findPostListCountAllBySearchTxt(String searchTxt) {
        return postMapper.selectPostListCountAllBySearchTxt(searchTxt);
    }

//    게시글 등록
    public int addPost(Post post) {
        System.out.println(post);
        return postMapper.createPost(post);
    }


//    전체 조회 (검색 조회 포함)
    public Optional<List<Post>> findPostList(int boardId, int startIdx, int limitCount, String order, String searchTxt) {
        return Optional.ofNullable(postMapper.selectPostList(boardId, startIdx, limitCount, order, searchTxt));
    }

}

package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.dto.response.post.RespAdminPostListDto;
import com.korit.dreampath_back.mapper.AdminPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminPostRepository {

    @Autowired
    private AdminPostMapper adminPostMapper;

    public List<RespAdminPostListDto> getAdminPostList(){
        return adminPostMapper.findPostAll();
    }

    public Optional<Boolean> deleteByPostId(int postId) {
        return adminPostMapper.deleteByPostId(postId) < 1 ? Optional.empty() : Optional.of(true);
    }
}

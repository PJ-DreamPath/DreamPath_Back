package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.dto.response.admin.RespAdminPostListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminPostMapper {

    List<RespAdminPostListDto> findPostAll();

    int deleteByPostId(int postId);
}

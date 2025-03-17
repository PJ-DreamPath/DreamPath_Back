package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
//    전체 조회 (검색 조회 포함)
    List<Post> selectPostList(
            @Param("boardId") int boardId,
            @Param("startIdx") int startIdx,
            @Param("limitCount") int limitCount,
            @Param("order") String order,
            @Param("SearchTxt")String SearchTxt
    );
}

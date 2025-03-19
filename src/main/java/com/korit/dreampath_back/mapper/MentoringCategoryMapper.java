package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.MentoringCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MentoringCategoryMapper {
    List<MentoringCategory> findAll();
}

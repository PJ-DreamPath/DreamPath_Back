package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.MentoringCategory;
import com.korit.dreampath_back.mapper.MentoringCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MentoringCategoryRepository {

    @Autowired
    private MentoringCategoryMapper mentoringCategoryMapper;
    public List<MentoringCategory> findAll() {
        return mentoringCategoryMapper.findAll();
    }
}

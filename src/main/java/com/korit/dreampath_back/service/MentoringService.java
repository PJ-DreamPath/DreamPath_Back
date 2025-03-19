package com.korit.dreampath_back.service;

import com.korit.dreampath_back.entity.MentoringCategory;
import com.korit.dreampath_back.repository.MentoringCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentoringService {
    @Autowired
    private MentoringCategoryRepository mentoringCategoryRepository;

    public List<MentoringCategory> findAllCategories() {
        return mentoringCategoryRepository.findAll();
    }
}

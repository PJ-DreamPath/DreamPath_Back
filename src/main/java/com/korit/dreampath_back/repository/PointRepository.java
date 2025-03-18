package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.PointPurchase;
import com.korit.dreampath_back.mapper.PointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointRepository {

    @Autowired
    private PointMapper pointMapper;

    public List<PointPurchase> getPointPurchase(int userId) {
        return pointMapper.getPointPurchase(userId);
    }

}

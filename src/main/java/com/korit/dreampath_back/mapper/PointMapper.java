package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.PointPurchase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointMapper {
    List<PointPurchase> getPointPurchase(int userId);
    String getPointPrice(int userId);
}

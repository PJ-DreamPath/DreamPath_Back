package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.PointPurchase;
import com.korit.dreampath_back.entity.PointPurchaseSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PointMapper {
    List<PointPurchase> getPointPurchase(
            int userId,
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("order") String order);

    int findAllPointPurchase(int userId);
}

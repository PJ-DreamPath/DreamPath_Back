package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.TicketPurchaseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TicketMapper {
    List<TicketPurchaseHistory> getTicketPurchaseList(
            int userId,
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("order") String order
    );

    int findAllTicketPurchase(int userId);
}

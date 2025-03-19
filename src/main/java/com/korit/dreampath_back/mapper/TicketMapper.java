package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.TicketPurchaseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TicketMapper {
    List<TicketPurchaseHistory> getTicketPurchaseList( int userId);
}

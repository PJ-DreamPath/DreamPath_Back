package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.TicketPurchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TicketMapper {
    List<TicketPurchase> getTicketPurchase(@Param("userId") int userId);
    String getPrice(int userId);



}

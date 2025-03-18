package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.response.RespPointPurchaseDto;
import com.korit.dreampath_back.entity.Point;
import com.korit.dreampath_back.entity.PointPurchase;
import com.korit.dreampath_back.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    public List<RespPointPurchaseDto> getPointPurchase(int userId) {
        List<PointPurchase> pointPurchaseList = pointRepository.getPointPurchase(userId);
        List<RespPointPurchaseDto> dtos = new ArrayList<>();
        for(PointPurchase pointPurchase : pointPurchaseList){
            RespPointPurchaseDto dto = RespPointPurchaseDto.builder()
                    .pointName(pointPurchase.getPoint().getPointName())
                    .pointPrice(pointPurchase.getPoint().getPointPrice())
                    .createdAt(pointPurchase.getCreatedAt())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
}

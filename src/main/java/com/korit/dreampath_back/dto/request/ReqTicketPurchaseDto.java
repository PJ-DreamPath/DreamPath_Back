package com.korit.dreampath_back.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqTicketPurchaseDto {

        private int page;
        private int limitCount;
        private String order;
        private String searchText;

}

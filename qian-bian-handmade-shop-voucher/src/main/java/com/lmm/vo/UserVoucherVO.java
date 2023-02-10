package com.lmm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVoucherVO {
    private Long id;
    private ShopVO shopVO;
    private String title;
    private Integer limit;
    private Integer stock;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime validUseStartTime;
    private LocalDateTime validUseEndTime;
}

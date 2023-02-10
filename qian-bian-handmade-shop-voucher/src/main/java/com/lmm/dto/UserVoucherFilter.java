package com.lmm.dto;

import com.lmm.constant.UserVoucherState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 芝麻
 * @date : 2023-02-10 14:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVoucherFilter {
    private Long shopId;
    private String userVoucherState = UserVoucherState.RECEIVED.getCode();
}

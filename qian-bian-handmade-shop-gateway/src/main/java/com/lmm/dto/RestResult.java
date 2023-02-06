package com.lmm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResult {
    private Object data;
    private Boolean isSuccess;
    private String errMessage;

    public static RestResult success(Object data) {
        return new RestResult(data, true, null);
    }

    public static RestResult fail(String errMessage) {
        return new RestResult(null, false, errMessage);
    }
}

package com.lmm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : 芝麻
 * @date : 2023-02-14 10:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCartItemDTO {
    public Long shopId;
    public List<Long> merchandiseIds;
}

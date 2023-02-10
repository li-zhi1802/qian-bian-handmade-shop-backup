package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.AddVoucherDTO;
import com.lmm.dto.PageResult;
import com.lmm.dto.UpdateVoucherDTO;
import com.lmm.entity.Voucher;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface VoucherService extends IService<Voucher> {

    Boolean addVoucher(AddVoucherDTO addVoucherDTO, Long userId);

    Boolean distributionVoucher(Long voucherId, Long userId);

    Boolean updateVoucher(UpdateVoucherDTO updateVoucherDTO, Long userId);

    PageResult<Voucher> pageQueryVouchersByShopId(Long shopId, Long pageNum);

    Boolean receiveVoucher(Long voucherId, Long userId);
}

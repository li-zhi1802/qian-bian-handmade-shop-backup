package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.PageResult;
import com.lmm.dto.UserVoucherFilter;
import com.lmm.entity.UserVoucher;
import com.lmm.vo.UserVoucherVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface UserVoucherService extends IService<UserVoucher> {

    PageResult<UserVoucherVO> listUserVoucherByPage(Long pageNum, UserVoucherFilter userVoucherState, Long userId);

    Boolean deleteUserVoucher(Long voucherId, Long userId);
}

package com.lmm.controller;

import com.lmm.dto.PageResult;
import com.lmm.dto.RestResult;
import com.lmm.dto.UserVoucherFilter;
import com.lmm.service.UserVoucherService;
import com.lmm.util.SecurityUtil;
import com.lmm.vo.UserVoucherVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Api(tags = "用户管理自己拥有的优惠券的接口")
@RestController
@RequestMapping("/user-voucher")
public class UserVoucherController {
    @Autowired
    private UserVoucherService userVoucherService;

    @ApiOperation("分页拿到登录用户领取的所有优惠券")
    @GetMapping("/{pageNum}")
    public PageResult<UserVoucherVO> listUserVoucherByPage(
            @PathVariable("pageNum") Long pageNum,
            @RequestBody UserVoucherFilter filter) {
        return userVoucherService.listUserVoucherByPage(pageNum, filter, SecurityUtil.getUser().getId());
    }

    @ApiOperation("删除指定优惠券")
    @DeleteMapping("/{voucherId}")
    public RestResult deleteUserVoucher(@PathVariable("voucherId") Long voucherId) {
        return RestResult.success(userVoucherService.deleteUserVoucher(voucherId, SecurityUtil.getUser().getId()));
    }
}

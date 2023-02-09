package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.PageResult;
import com.lmm.entity.StarShop;
import com.lmm.vo.StarShopVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface StarShopService extends IService<StarShop> {

    Boolean addOrRemoveShop(Long shopId, Long userId);

    PageResult<StarShopVO> listStarShopByPage(Long pageNum, Long userId);

    Boolean deleteStarShops(List<Long> shopId, Long userId);
}

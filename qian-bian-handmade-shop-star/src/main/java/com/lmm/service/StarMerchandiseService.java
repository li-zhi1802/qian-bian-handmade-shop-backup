package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.PageResult;
import com.lmm.entity.StarMerchandise;
import com.lmm.vo.StarMerchandiseVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface StarMerchandiseService extends IService<StarMerchandise> {

    Boolean addOrRemoveMerchandise(Long merchandiseId, Long userId);

    PageResult<StarMerchandiseVO> listStarMerchandiseByPage(Long pageNum, Long userId);

    Boolean deleteStarMerchandises(List<Long> merchandiseId, Long userId);
}

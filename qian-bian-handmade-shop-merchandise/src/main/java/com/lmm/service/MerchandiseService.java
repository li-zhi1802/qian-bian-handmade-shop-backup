package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.PublishMerchandiseDTO;
import com.lmm.dto.UpdateMerchandiseDTO;
import com.lmm.entity.Merchandise;
import com.lmm.vo.MerchandiseVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface MerchandiseService extends IService<Merchandise> {

    MerchandiseVO getDetailMerchandiseById(Long id);

    Boolean publishMerchandise(PublishMerchandiseDTO publishMerchandiseDTO, Long userId);

    Boolean lowerShelfMerchandise(Long merchandiseId);

    Boolean updateMerchandise(UpdateMerchandiseDTO updateMerchandiseDTO);
}

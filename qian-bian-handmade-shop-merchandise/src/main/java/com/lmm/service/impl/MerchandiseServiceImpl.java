package com.lmm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.entity.Merchandise;
import com.lmm.mapper.MerchandiseMapper;
import com.lmm.service.MerchandiseService;
import com.lmm.vo.MerchandiseVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class MerchandiseServiceImpl extends ServiceImpl<MerchandiseMapper, Merchandise> implements MerchandiseService {

    @Override
    public MerchandiseVO getDetailMerchandiseById(Long id) {
        // TODO: 2023/2/10 待实现
        return null;
    }
}

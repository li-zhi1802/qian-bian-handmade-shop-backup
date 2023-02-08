package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.PageResult;
import com.lmm.entity.Footmark;
import com.lmm.vo.FootmarkVO;

public interface FootmarkService extends IService<Footmark> {

    /**
     * 返回指定页码的足迹
     *
     * @param userId
     * @param pageNum
     * @return
     */
    PageResult<FootmarkVO> listFootmarksByUserId(Long userId, Long pageNum);
}

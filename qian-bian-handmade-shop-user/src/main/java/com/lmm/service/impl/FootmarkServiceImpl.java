package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.MerchandiseClient;
import com.lmm.dto.PageResult;
import com.lmm.entity.Footmark;
import com.lmm.entity.Merchandise;
import com.lmm.mapper.FootmarkMapper;
import com.lmm.service.FootmarkService;
import com.lmm.vo.FootmarkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.lmm.constant.SystemConstant.PAGE_SIZE;

@Service
public class FootmarkServiceImpl extends ServiceImpl<FootmarkMapper, Footmark> implements FootmarkService {
    @Autowired
    private MerchandiseClient merchandiseClient;

    @Override
    public PageResult<FootmarkVO> listFootmarksByUserId(Long userId, Long pageNum) {
        // 分页查询
        Page<Footmark> pageQuery = page(
                new Page<>(pageNum, PAGE_SIZE),
                new LambdaQueryWrapper<Footmark>()
                        .eq(Footmark::getUserId, userId)
                        .orderByDesc(Footmark::getLastLookTime)
        );
        return PageResult.success(
                // 组装数据
                pageQuery.getRecords().stream().map(f -> {
                    // 拿到完整商品信息
                    Merchandise merchandise = merchandiseClient.getMerchandiseById(f.getMerchandiseId());
                    // 复制
                    FootmarkVO footmarkVO = BeanUtil.copyProperties(merchandise, FootmarkVO.class);
                    // 修改图像的uri为集合的第一个照片
                    footmarkVO.setPicUri(JSONUtil.toList(merchandise.getPicUris(), String.class).get(0));
                    return footmarkVO;
                }).collect(Collectors.toList()),
                pageNum,
                pageQuery.getTotal()
        );
    }
}

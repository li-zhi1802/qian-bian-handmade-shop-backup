package com.lmm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.client.OrderClient;
import com.lmm.client.ShopClient;
import com.lmm.client.UserClient;
import com.lmm.constant.MerchandiseOrderState;
import com.lmm.dto.PublishMerchandiseCommentDTO;
import com.lmm.dto.ReviewMerchandiseCommentDTO;
import com.lmm.dto.UserDTO;
import com.lmm.entity.Merchandise;
import com.lmm.entity.MerchandiseComment;
import com.lmm.entity.Shop;
import com.lmm.exception.QianBianException;
import com.lmm.mapper.MerchandiseCommentMapper;
import com.lmm.service.MerchandiseCommentService;
import com.lmm.service.MerchandiseService;
import com.lmm.vo.MerchandiseCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.lmm.constant.SystemConstant.COMMENT_PAGE_SIZE;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class MerchandiseCommentServiceImpl extends ServiceImpl<MerchandiseCommentMapper, MerchandiseComment> implements MerchandiseCommentService {
    @Autowired
    private UserClient userClient;
    @Autowired
    private ShopClient shopClient;
    @Autowired
    private MerchandiseService merchandiseService;
    @Autowired
    private OrderClient orderClient;

    @Override
    public List<MerchandiseCommentVO> listCommentsByMerchandiseId(Long merchandiseId, Long pageNum) {
        Page<MerchandiseComment> pageQuery = page(new Page<>(pageNum, COMMENT_PAGE_SIZE), new LambdaQueryWrapper<MerchandiseComment>().eq(MerchandiseComment::getMerchandiseId, merchandiseId).orderByDesc(MerchandiseComment::getPublishTime));
        List<MerchandiseComment> merchandiseComments = pageQuery.getRecords();
        List<MerchandiseCommentVO> merchandiseCommentVOs = merchandiseComments.stream().map(mc -> {
            MerchandiseCommentVO merchandiseCommentVO = BeanUtil.copyProperties(mc, MerchandiseCommentVO.class);
            merchandiseCommentVO.setPicUris(JSONUtil.toList(mc.getPicUris(), String.class));
            merchandiseCommentVO.setUser(BeanUtil.copyProperties(userClient.findUserById(mc.getUserId()), UserDTO.class));
            return merchandiseCommentVO;
        }).collect(Collectors.toList());
        // 组装子评论
        assembleComments(merchandiseCommentVOs);
        return merchandiseCommentVOs;
    }

    @Override
    public Boolean publishComment(PublishMerchandiseCommentDTO publishMerchandiseCommentDTO, Long userId) {
        if (publishMerchandiseCommentDTO.getOrderId() == null) {
            throw new QianBianException("发布评论需要提供订单id");
        }
        if (publishMerchandiseCommentDTO.getMerchandiseId() == null) {
            throw new QianBianException("发布评论需要提供商品id");
        }
        MerchandiseComment merchandiseComment = BeanUtil.copyProperties(publishMerchandiseCommentDTO, MerchandiseComment.class);
        merchandiseComment.setUserId(userId);
        merchandiseComment.setPublishTime(LocalDateTime.now());
        merchandiseComment.setPicUris(JSONUtil.toJsonStr(publishMerchandiseCommentDTO.getPicUris()));
        // 更新商铺评分
        Long shopId = merchandiseService.lambdaQuery().eq(Merchandise::getId, merchandiseComment.getMerchandiseId()).select(Merchandise::getShopId).one().getShopId();
        Shop shop = shopClient.getShopById(shopId);
        BigDecimal nextServiceScoreSum = shop.getServiceScoreSum().add(merchandiseComment.getServiceScore());
        BigDecimal nextDescriptionScoreSum = shop.getDescriptionScoreSum().add(merchandiseComment.getDescriptionScore());
        BigDecimal nextLogisticsScoreSum = shop.getLogisticsScoreSum().add(merchandiseComment.getLogisticsScore());
        shop.setCommentSum(shop.getCommentSum() + 1);
        BigDecimal size = new BigDecimal(shop.getCommentSum());
        shop.setServiceScoreSum(nextServiceScoreSum);
        shop.setServiceAvgScore(nextServiceScoreSum.divide(size, 2, RoundingMode.FLOOR));
        shop.setDescriptionScoreSum(nextDescriptionScoreSum);
        shop.setDescriptionAvgScore(nextDescriptionScoreSum.divide(size, 2, RoundingMode.FLOOR));
        shop.setLogisticsScoreSum(nextLogisticsScoreSum);
        shop.setLogisticsAvgScore(nextLogisticsScoreSum.divide(size, 2, RoundingMode.FLOOR));
        Boolean success = shopClient.updateAvgScore(shop);
        if (success == null || !success) {
            throw new QianBianException("更新店铺评分失败，评论保存失败");
        }
        return orderClient.updateOrderState(
                MerchandiseOrderState.ORDER_COMPLETION.getCode(),
                publishMerchandiseCommentDTO.getOrderId()) && save(merchandiseComment);
    }

    @Override
    public Boolean reviewComment(ReviewMerchandiseCommentDTO reviewMerchandiseCommentDTO, Long userId) {
        if (reviewMerchandiseCommentDTO.getParentId() == null) {
            throw new QianBianException("追评请提供父评论id");
        }
        MerchandiseComment reviewComment = BeanUtil.copyProperties(reviewMerchandiseCommentDTO, MerchandiseComment.class);
        reviewComment.setUserId(userId);
        reviewComment.setPublishTime(LocalDateTime.now());
        return save(reviewComment);
    }

    private void assembleComments(List<MerchandiseCommentVO> merchandiseCommentVOs) {
        if (merchandiseCommentVOs == null || merchandiseCommentVOs.isEmpty()) {
            return;
        }
        for (MerchandiseCommentVO mcv : merchandiseCommentVOs) {
            mcv.setChildMerchandiseComments(
                    lambdaQuery().eq(MerchandiseComment::getParentId, mcv.getId()).list().stream().map(mc -> {
                        MerchandiseCommentVO merchandiseCommentVO = BeanUtil.copyProperties(mc, MerchandiseCommentVO.class);
                        merchandiseCommentVO.setPicUris(JSONUtil.toList(mc.getPicUris(), String.class));
                        merchandiseCommentVO.setUser(BeanUtil.copyProperties(userClient.findUserById(mc.getUserId()), UserDTO.class));
                        return merchandiseCommentVO;
                    }).collect(Collectors.toList())
            );
            // 递归组装
            assembleComments(mcv.getChildMerchandiseComments());
        }
    }
}

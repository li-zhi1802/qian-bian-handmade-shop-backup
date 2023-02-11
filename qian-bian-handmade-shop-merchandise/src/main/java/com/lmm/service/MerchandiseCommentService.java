package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.PublishMerchandiseCommentDTO;
import com.lmm.dto.ReviewMerchandiseCommentDTO;
import com.lmm.entity.MerchandiseComment;
import com.lmm.vo.MerchandiseCommentVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface MerchandiseCommentService extends IService<MerchandiseComment> {

    List<MerchandiseCommentVO> listCommentsByMerchandiseId(Long merchandiseId, Long pageNum);

    Boolean publishComment(PublishMerchandiseCommentDTO publishMerchandiseCommentDTO, Long userId);

    Boolean reviewComment(ReviewMerchandiseCommentDTO publishMerchandiseCommentDTO, Long userId);
}

package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.MerchandiseOrderDTO;
import com.lmm.dto.PageResult;
import com.lmm.entity.MerchandiseOrder;
import com.lmm.vo.MerchandiseOrderVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface MerchandiseOrderService extends IService<MerchandiseOrder> {

    String generateOrders(List<MerchandiseOrderDTO> ordersToBeGenerated, Long userId);

    PageResult<MerchandiseOrderVO> listOrderByPage(Long pageNum, String orderState, Long userId);

    Boolean updateOrderState(String orderId, String nextState);
}

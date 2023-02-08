package com.lmm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmm.dto.DictionaryItem;
import com.lmm.entity.Dictionary;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
public interface DictionaryService extends IService<Dictionary> {

    /**
     * 返回系统的字典
     *
     * @return
     */
    Map<String, DictionaryItem> queryAll();
}

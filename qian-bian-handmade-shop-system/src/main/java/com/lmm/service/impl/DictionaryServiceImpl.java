package com.lmm.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmm.dto.DictionaryItem;
import com.lmm.dto.DictionaryValue;
import com.lmm.entity.Dictionary;
import com.lmm.mapper.DictionaryMapper;
import com.lmm.service.DictionaryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 芝麻
 * @since 2023-02-06
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Override
    @Cacheable(cacheNames = "system", key = "'dictionary'")
    public Map<String, DictionaryItem> queryAll() {
        Map<String, DictionaryItem> dictionaryItems = new HashMap<>(32);
        list().forEach(dictionary -> {
            Map<String, String> dictionaryValueMap = new HashMap<>(8);
            JSONUtil.toList(dictionary.getValue(), DictionaryValue.class).forEach(dv -> dictionaryValueMap.put(dv.getCode(), dv.getDesc()));
            DictionaryItem dictionaryItem = new DictionaryItem();
            dictionaryItem.setId(dictionary.getId());
            dictionaryItem.setDescription(dictionary.getDescription());
            dictionaryItem.setDictionary(dictionaryValueMap);
            dictionaryItems.put(dictionary.getKey(), dictionaryItem);
        });
        return dictionaryItems;
    }
}

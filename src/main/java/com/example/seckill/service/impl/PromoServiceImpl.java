package com.example.seckill.service.impl;

import com.example.seckill.dao.PromoDOMapper;
import com.example.seckill.dataobject.PromoDO;
import com.example.seckill.service.PromoService;
import com.example.seckill.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cheng
 * @date 2022-05-02 17:45:19
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        List<PromoDO> promoDOList = promoDOMapper.selectByItemId(itemId);
        PromoDO promoDO = (promoDOList == null || promoDOList.size() == 0) ? null : promoDOList.get(0);
        PromoModel promoModel = convertModelFromDO(promoDO);
        if (promoModel == null) return null;
        // 判断当前时间秒杀活动是否正在进行或尚未开始
        DateTime now = new DateTime();
        if (promoModel.getStartDate().isAfterNow()) promoModel.setStatus(1);
        else if (promoModel.getEndDate().isAfterNow()) promoModel.setStatus(2);
        else promoModel.setStatus(3);
        return promoModel;
    }

    @Override
    public PromoModel getPromoByPromoId(Integer promoId) {
        PromoDO promoDO = promoDOMapper.selectByPromoId(promoId);
        return convertModelFromDO(promoDO);
    }

    private PromoModel convertModelFromDO(PromoDO promoDO) {
        if (promoDO == null) return null;
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));
        return promoModel;
    }
}

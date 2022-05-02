package com.example.seckill.service;

import com.example.seckill.service.model.PromoModel;

public interface PromoService {
    PromoModel getPromoByItemId(Integer itemId);

    PromoModel getPromoByPromoId(Integer promoId);
}

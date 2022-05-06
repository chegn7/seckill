package com.example.seckill.common;

/**
 * @author cheng
 * @date 2022-05-05 20:16:08
 */
public class RedisKeyUtil {
    public static final String SPLIT = "_";
    public static final String ITEM_DETAIL = "ITEM_DETAIL";
    public static String getItemDetailKey(int itemId) {
        return ITEM_DETAIL + SPLIT + itemId;
    }
}

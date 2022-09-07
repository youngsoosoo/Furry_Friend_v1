package com.ff.furry_friend.dto.KakaoPay;

import lombok.Data;

@Data
public class AmountVO {
    private Integer total, tax_free, vat, point, discount;
}

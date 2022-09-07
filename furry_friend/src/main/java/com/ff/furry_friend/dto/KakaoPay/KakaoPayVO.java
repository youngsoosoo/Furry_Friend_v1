package com.ff.furry_friend.dto.KakaoPay;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayVO {
    private String tid, next_redirect_pc_url;
    private Date created_at;
}

package com.ff.furry_friend.service;

import com.ff.furry_friend.dto.KakaoPay.KakaoPayApprovalVO;
import com.ff.furry_friend.dto.KakaoPay.KakaoPayVO;
import com.ff.furry_friend.entity.product;
import com.ff.furry_friend.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service    //해당 클래스는 Service
@Log
public class KakaopayService {
    private static final String HOST = "https://kapi.kakao.com";

    @Autowired // 객체 자동 연결
    private UserRepository userRepository;
    private KakaoPayVO kakaoPayVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    public String kakaoPayReady(product pro, String userid) {  //결제를 함.

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "3ef51cabada59b06583801b742c8ec3b");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");            // 가맹점 코드
        params.add("partner_order_id", Long.toString(pro.getPro_id()));     // 가맹점 주문 번호
        params.add("partner_user_id", userid);    // 가맹점 회원 아이디
        params.add("item_name", pro.getPro_name());            // 상품명
        params.add("quantity", pro.getPro_number());                // 상품 수량
        params.add("total_amount", Integer.toString(pro.getPro_price()));         // 상품 총액
        params.add("tax_free_amount", "100");       // 상품 비과세 금액
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");    // 결제 성공시
        params.add("cancel_url", "http://localhost:8080/kakaoPay");       // 결제 취소시
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");    // 결제 실패시

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayVO.class);

            log.info("" + kakaoPayVO);


            return kakaoPayVO.getNext_redirect_pc_url();

        } catch (RestClientException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "/pay";
    }
    public KakaoPayApprovalVO kakaoPayInfo(String pg_token, product pro, String userid) {  //결제 정보를 알려줌

        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "3ef51cabada59b06583801b742c8ec3b");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayVO.getTid());
        params.add("partner_order_id", "1");
        params.add("partner_user_id", userid);
        params.add("pg_token", pg_token);
        params.add("total_amount", String.valueOf(pro.getPro_price()));

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            return kakaoPayApprovalVO;

        } catch (RestClientException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}

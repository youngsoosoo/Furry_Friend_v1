<<<<<<< HEAD
package com.ff.furry_friend.service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CertifiedService {
    public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) {
        String api_key = "NCSQCIUMK8CNIFSV";
        String api_secret = "RNPY1PPVBGVHIRHP49ZRHJGVRHNZT3I3";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", userPhoneNumber);    // 수신전화번호
        params.put("from", "010-8248-8674");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "[TEST] 인증번호는" + "["+randomNumber+"]" + "입니다."); // 문자 내용 입력
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }
}
=======
package com.ff.furry_friend.repository.service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CertifiedService {
    public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) {
        String api_key = "NCSQCIUMK8CNIFSV";
        String api_secret = "RNPY1PPVBGVHIRHP49ZRHJGVRHNZT3I3";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", userPhoneNumber);    // 수신전화번호
        params.put("from", "010-8248-8674");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "[TEST] 인증번호는" + "["+randomNumber+"]" + "입니다."); // 문자 내용 입력
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }
}
>>>>>>> 8e03e7394615a9cc04840a4bff68609e8d488134

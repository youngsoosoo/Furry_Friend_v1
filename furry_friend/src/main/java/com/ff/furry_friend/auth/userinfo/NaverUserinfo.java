package com.ff.furry_friend.auth.userinfo;

import com.ff.furry_friend.auth.userinfo.OAuth2Userinfo;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class NaverUserinfo implements OAuth2Userinfo {
    private Map<String, Object> attributes; //OAuth2User.getAttributes();
    private Map<String, Object> attributesResponse;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return attributesResponse.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return attributesResponse.get("email").toString();
    }

    @Override
    public String getName() {
        return attributesResponse.get("name").toString();
    }
}

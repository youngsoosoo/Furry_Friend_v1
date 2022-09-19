package com.ff.furry_friend.auth.userinfo;

import com.ff.furry_friend.auth.userinfo.OAuth2Userinfo;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class NaverUserinfo implements OAuth2Userinfo {
    private Map<String, Object> attributes; //OAuth2User.getAttributes();

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }
}

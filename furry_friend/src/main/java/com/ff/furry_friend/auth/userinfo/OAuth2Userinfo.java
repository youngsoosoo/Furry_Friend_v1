package com.ff.furry_friend.auth.userinfo;

import java.util.Map;

public interface OAuth2Userinfo {
    Map<String, Object> getAttributes();
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}

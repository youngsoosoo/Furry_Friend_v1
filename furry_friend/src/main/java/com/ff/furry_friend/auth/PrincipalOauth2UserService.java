package com.ff.furry_friend.auth;

import com.ff.furry_friend.auth.userinfo.GoogleUserinfo;
import com.ff.furry_friend.auth.userinfo.NaverUserinfo;
import com.ff.furry_friend.auth.userinfo.OAuth2Userinfo;
import com.ff.furry_friend.entity.Role;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2Userinfo oAuth2Userinfo = null;	//추가
        String provider = userRequest.getClientRegistration().getRegistrationId();

        //추가
        if(provider.equals("google")){
            oAuth2Userinfo = new GoogleUserinfo(oAuth2User.getAttributes());
        }
        else if(provider.equals("naver")){
            oAuth2Userinfo = new NaverUserinfo(oAuth2User.getAttributes());
        }

        String providerId = oAuth2Userinfo.getProviderId();	//수정
        String username = provider+"_"+providerId;

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);

        String email = oAuth2Userinfo.getEmail();	//수정
        Role role = Role.ROLE_USER;

        user byUsername = userRepository.findByUserid(username);

        //DB에 없는 사용자라면 회원가입처리
        if(byUsername == null){
            byUsername = user.oauth2Register()
                    .id(username).pw(password).role(role)
                    .provider(provider).providerId(providerId)
                    .build();
            userRepository.save(byUsername);
        }

        return new PrincipalDetails(byUsername, oAuth2Userinfo);	//수정
    }
}

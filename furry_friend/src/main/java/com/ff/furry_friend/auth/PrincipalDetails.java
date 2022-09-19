package com.ff.furry_friend.auth;

import com.ff.furry_friend.auth.userinfo.OAuth2Userinfo;
import com.ff.furry_friend.entity.user;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@ToString
public class PrincipalDetails implements UserDetails, OAuth2User {

    private user user;
    //private Map<String, Object> attributes;
    private OAuth2Userinfo oAuth2Userinfo;

    //UserDetails : Form 로그인 시 사용
    public PrincipalDetails(com.ff.furry_friend.entity.user user) {
        this.user = user;
    }

    //OAuth2User : OAuth2 로그인 시 사용
    //public PrincipalDetails(User user, Map<String, Object> attributes) {
    //    //PrincipalOauth2UserService 참고
    //    this.user = user;
    //    this.attributes = attributes;
    //}

    public PrincipalDetails(user user, OAuth2Userinfo oAuth2Userinfo) {
        this.user = user;
        this.oAuth2Userinfo = oAuth2Userinfo;
    }


    /**
     * UserDetails 구현
     * 해당 유저의 권한목록 리턴
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().toString();
            }
        });
        return collect;
    }

    /**
     * UserDetails 구현
     * 비밀번호를 리턴
     */
    @Override
    public String getPassword() {
        return user.getPw();
    }

    /**
     * UserDetails 구현
     * PK값을 반환해준다
     */
    @Override
    public String getUsername() {
        return user.getId();
    }

    /**
     * UserDetails 구현
     * 계정 만료 여부
     *  true : 만료안됨
     *  false : 만료됨
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 잠김 여부
     *  true : 잠기지 않음
     *  false : 잠김
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 비밀번호 만료 여부
     *  true : 만료 안됨
     *  false : 만료됨
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 활성화 여부
     *  true : 활성화됨
     *  false : 활성화 안됨
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    /**
     * OAuth2User 구현
     * @return
     */
    @Override
    public Map<String, Object> getAttributes() {
        //return attributes;
        return oAuth2Userinfo.getAttributes();
    }

    /**
     * OAuth2User 구현
     * @return
     */
    @Override
    public String getName() {
        //String sub = attributes.get("sub").toString();
        //return sub;
        return oAuth2Userinfo.getProviderId();
    }
}

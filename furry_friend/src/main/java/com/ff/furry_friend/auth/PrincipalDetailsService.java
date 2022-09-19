package com.ff.furry_friend.auth;

import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        user byUsername = userRepository.findByUserid(username);
        if(byUsername != null){
            return new PrincipalDetails(byUsername);
        }
        return null;
    }
}
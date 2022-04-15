package com.tms.web.configuration;

import com.tms.web.entities.repositories.UserRepository;
import com.tms.web.entities.security.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

public class WebSecurityAuthProvider implements AuthenticationProvider {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userRepository.findByUsername(username);

        if (user != null && user.getUsername().equals(username)) {
            if (!passwordEncoder.matches(password,user.getPassword())) {
                throw new AccessDeniedException("WrongPassword");
            }
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return new UsernamePasswordAuthenticationToken(user,password,authorities);
        }else {
            throw new AccessDeniedException("Username not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

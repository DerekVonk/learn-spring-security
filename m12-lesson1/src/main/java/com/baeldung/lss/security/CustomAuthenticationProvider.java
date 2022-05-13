package com.baeldung.lss.security;

import com.baeldung.lss.persistence.UserRepository;
import com.baeldung.lss.web.model.User;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * --> class description here
 * <p>
 *
 * @author derek.vonk@globalorange.nl
 * @created 13/05/2022
 * @project m12-lesson1
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        String verificationCode = ((CustomWebAuthenticationDetails) authentication.getDetails()).getVerificationCode();
        User user = userRepository.findByEmail(userName);
        if ((user == null) || !encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid Username or Password");
        }

        // verification code check here
        Totp topt = new Totp(user.getSecret());
        try {
            if (!topt.verify(verificationCode)) {
                throw new BadCredentialsException("Invalid Verification Code");
            }
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Verification Code");
        }

        return new UsernamePasswordAuthenticationToken(user, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

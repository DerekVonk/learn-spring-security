package com.baeldung.lss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class LssSecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;

    public LssSecurityConfig(PasswordEncoder passwordEncoder) {
        super();
        this.passwordEncoder = passwordEncoder;
    }

    //

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off 
        auth.
                inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("user").password(passwordEncoder.encode("pass")).roles("USER").and()
                .withUser("admin").password(passwordEncoder.encode("pass")).roles("ADMIN")
        ;
    } // @formatter:on

    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http
                .authorizeRequests()

//                .antMatchers("/secured").access("hasIpAddress('::1')")
//                .antMatchers("/secured").access("hasRole('ROLE_USER')")
//                .antMatchers("/secured").not().access("hasIpAddress('::1')")
//                .antMatchers("/secured").anonymous() or .access("isAnonymous()")
//                .antMatchers("/secured").access("request.method != 'POST'")
//                .antMatchers("/secured").access("hasRole('ROLE_ADMIN') or principal.username == 'user'")

                // in production system, it is no problem at all to use URL level authorization AND method-level
                // authorization together for more fine-grained control
                .anyRequest().permitAll()

                .and().httpBasic()

                .and()
                .formLogin().
                loginPage("/login").permitAll().
                loginProcessingUrl("/doLogin")

                .and()
                .logout().permitAll().logoutUrl("/logout")

                .and()
                .csrf().disable()
        ;
    }

}

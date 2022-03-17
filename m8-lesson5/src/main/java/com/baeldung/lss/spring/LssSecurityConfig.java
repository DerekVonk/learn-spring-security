package com.baeldung.lss.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.baeldung.lss.model.User;
import com.baeldung.lss.persistence.UserRepository;

@EnableWebSecurity
public class LssSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    public LssSecurityConfig() {
        super();
    }

    //

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {// @formatter:off
        auth.userDetailsService(userDetailsService);
    }// @formatter:on

    @Override
    protected void configure(HttpSecurity http) throws Exception {// @formatter:off
        http
        .authorizeRequests()
                .antMatchers("/badUser*","/js/**").permitAll()
                .anyRequest().authenticated()

        .and()
        .formLogin().
            loginPage("/login").permitAll().
            loginProcessingUrl("/doLogin")
        
        .and()
        .logout().permitAll().logoutUrl("/logout")
        
        .and().sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry()).and().sessionFixation().none()

        .and()
        .csrf().disable()
        ;
    } // @formatter:on

    @PostConstruct
    private void saveTestUser() {
        final User user = new User();
        user.setEmail("test@email.com");
        user.setPassword("pass");
        userRepository.save(user);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}

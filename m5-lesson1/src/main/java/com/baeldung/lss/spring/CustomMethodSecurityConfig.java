package com.baeldung.lss.spring;

import com.baeldung.lss.security.CustomMethodSecurityExpressionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * --> class description here
 * <p>
 *
 * @author derek.vonk@globalorange.nl
 * @created 04/05/2022
 * @project m5-lesson1
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        CustomMethodSecurityExpressionHandler expressionHandler = new CustomMethodSecurityExpressionHandler();
        return expressionHandler;
    }

}

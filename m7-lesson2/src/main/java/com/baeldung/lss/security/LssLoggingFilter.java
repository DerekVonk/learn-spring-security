package com.baeldung.lss.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * --> class description here
 * <p>
 *
 * @author derek.vonk@globalorange.nl
 * @created 11/05/2022
 * @project m7-lesson2
 */
@Component
public class LssLoggingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURL().toString();
        String queryString =
                Optional.ofNullable(request.getQueryString()).map(value -> "?" + value).orElse("");
        logger.info(String.format("applying LssLoggingFilter for URI: %s%s", url, queryString));

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

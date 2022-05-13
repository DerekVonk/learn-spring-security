package com.baeldung.lss.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * --> Custom web authentication details class for extracting the 2FA code/parameter from the request and
 * storing it for later use.
 * <p>
 *
 * @author derek.vonk@globalorange.nl
 * @created 13/05/2022
 * @project m12-lesson1
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private String verificationCode;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.verificationCode = request.getParameter("code");
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}

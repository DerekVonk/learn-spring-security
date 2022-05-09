package com.baeldung.lss.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Custom implementation of Programmatic PermissionEvaluation
 *
 * We are extending a package-private implementation of the MethodSecurityExpressionRoot.
 * This is why we are simply copying the implementation of that class as for this case, it serves
 * our purpose.
 *
 * <p>
 *
 * @author derek.vonk@globalorange.nl
 * @created 04/05/2022
 * @project m5-lesson1
 */
public class MySecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private Object filterObject;
    private Object returnObject;
    private Object target;

    /**
     * Creates a new instance
     *
     * @param authentication the {@link Authentication} to use. Cannot be null.
     */
    public MySecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }
    
    // 
    
    public boolean isAdmin() {
        if (!(getPrincipal() instanceof User)) {
            return false;
        }

        User user = (User) getPrincipal();
        return user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    public Object getFilterObject() {
        return this.filterObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public Object getReturnObject() {
        return this.returnObject;
    }

    /**
     * Sets the "this" property for use in expressions. Typically this will be the "this"
     * property of the {@code JoinPoint} representing the method invocation which is being
     * protected.
     * @param target the target object on which the method in is being invoked.
     */
    void setThis(Object target) {
        this.target = target;
    }

    public Object getThis() {
        return this.target;
    }
}

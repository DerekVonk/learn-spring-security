package com.baeldung.lss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --> class description here
 * <p>
 *
 * @author derek.vonk@globalorange.nl
 * @created 13/05/2022
 * @project m8-lesson4
 */
@Service
public class ActiveUsersService {

    @Autowired
    SessionRegistry sessionRegistry;

    public List<String> getAllActiveUsers() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        User[] users = allPrincipals.toArray(new User[allPrincipals.size()]);

        return Arrays.stream(users)
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(u -> u.getUsername())
                .collect(Collectors.toList());
    }
}

package com.baeldung.lss.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * --> class description here
 * <p>
 *
 * @author derek.vonk@globalorange.nl
 * @created 11/05/2022
 * @project m7-lesson2
 */
@Component
public class AsyncBean {

    @Async
    public void asyncCall() {
        System.out.println();
    }
}

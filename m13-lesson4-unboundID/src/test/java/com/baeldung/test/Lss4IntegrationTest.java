package com.baeldung.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baeldung.lss.spring.LssApp4;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LssApp4.class)
public class Lss4IntegrationTest {

    @Test
    public void whenLoadApplication_thenSuccess() {

    }
}
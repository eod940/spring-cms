package com.zerobase.cms.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailSendServiceTest {

    @Autowired
    private EmailSendService emailSendService;

    @Test
    void emailTest() throws Exception {
        //given
        String response = emailSendService.sendEmail();
        System.out.println(response);
        //when

        //then
    }
}
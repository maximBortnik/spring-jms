package com.springjms.springjms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Slf4j
@Component
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public void send(String destination, Object object, String objectStatus){
        log.info("[Sender] Sending an object to activemq broker: {}", object);
        jmsTemplate.convertAndSend(destination, object, message -> {
            message.setStringProperty("status", objectStatus);
            message.setJMSTimestamp(Calendar.getInstance().getTime().getTime());
            return message;
        });
    }
}

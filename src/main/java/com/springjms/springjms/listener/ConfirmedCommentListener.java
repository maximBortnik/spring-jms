package com.springjms.springjms.listener;

import lombok.extern.slf4j.Slf4j;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Slf4j
public class ConfirmedCommentListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                log.info("[ConfirmedCommentListener] Message is received after processing from activemq: {}", text);
            } catch (JMSException e) {
                log.error("Cannot get a text from message.", e);
            }
        }
    }
}

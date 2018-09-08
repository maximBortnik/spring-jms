package com.springjms.springjms.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ErrorHandler;

@Slf4j
public class JmsErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        log.error("Handling error in listening for messages, error: " + t.getMessage());
    }
}

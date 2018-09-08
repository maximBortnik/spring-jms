package com.springjms.springjms.service;

import com.springjms.springjms.Constant;
import com.springjms.springjms.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HandleBook {

    public JmsResponse<Message<Book>> handleBook(Book book, String status) {
        switch (status.toUpperCase()) {
            case "ADD":
                return JmsResponse.forQueue(add(book), Constant.QUEUE_ADD);
            case "UPDATE":
                return JmsResponse.forQueue(update(book), Constant.QUEUE_ADD);
            case "DELETE":
                return JmsResponse.forQueue(delete(book), Constant.QUEUE_DELETE);
            default:
                throw new IllegalArgumentException("Status " + status + " is not support!");
        }
    }

    private Message<Book> add(Book book){
        log.info("ADDING A NEW BOOK TO DB");
        //TODO - some type of db operation
        return build(book, "ADDED");
    }
    private Message<Book> update(Book book){
        log.info("UPDATING A BOOK TO DB");
        //TODO - some type of db operation
        return build(book, "UPDATED");
    }
    private Message<Book> delete(Book book){
        log.info("DELETING BOOK FROM DB");
        //TODO - some type of db operation
        return build(book, "DELETED");
    }

    private Message<Book> build(Book book, String status){
        return MessageBuilder
                .withPayload(book)
                .setHeader("status", status)
                .build();
    }
}

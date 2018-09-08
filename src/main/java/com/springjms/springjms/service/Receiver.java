package com.springjms.springjms.service;

import com.springjms.springjms.model.Book;
import com.springjms.springjms.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.springjms.springjms.Constant.BOOK_QUEUE;
import static com.springjms.springjms.Constant.COMMENT_QUEUE;
import static com.springjms.springjms.Constant.MESSAGE_QUEUE;
import static com.springjms.springjms.Constant.QUEUE_ADD;

@Slf4j
@Component
public class Receiver {

    @Autowired
    private HandleComment handelComment;
    @Autowired
    private HandleBook handleBook;

    @JmsListener(destination = MESSAGE_QUEUE)
    public void receiveMessage(@Payload String message,
                               @Header("status") String status){
        log.info("[Receiver] Message is received from activemq: {} with status: {}", message, status);
    }

    @JmsListener(destination = COMMENT_QUEUE)
    public void receiveBook(@Payload Comment comment,
                            @Header("status") String status) {
        log.info("[Receiver] Comment is received from activemq: {} with status: {}", comment, status);
        handelComment.handleComment(comment, status);
    }

    @JmsListener(destination = BOOK_QUEUE)
    @SendTo(value = QUEUE_ADD)
    @Transactional
    public JmsResponse<Message<Book>> receiveBookAndSend(@Payload Book book,
                                   @Header("status") String status) {
        log.info("[Receiver-SendTo] Book is received from activemq: {} with status: {}", book, status);
        JmsResponse<Message<Book>> messageJmsResponse = handleBook.handleBook(book, status);
        log.info("[Receiver-SendTo] Sending book after handling: {}", book);
        return messageJmsResponse;
    }
}

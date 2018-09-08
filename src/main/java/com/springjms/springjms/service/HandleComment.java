package com.springjms.springjms.service;

import com.springjms.springjms.model.Comment;
import com.springjms.springjms.model.ProcessedComment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

import static com.springjms.springjms.Constant.COMMENT_QUEUE_CONFIRM;

@Slf4j
@Component
public class HandleComment {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Transactional
    public void handleComment(Comment comment, String status) {
        if (Objects.nonNull(comment)) {
            log.info("[HandleComment] Handling comment: {}", comment);
            ProcessedComment processedComment = new ProcessedComment();
            processedComment.setComment(comment);
            processedComment.setDate(new Date());
            processedComment.setConfirm(true);

            Message<ProcessedComment> processedCommentMessage
                    = MessageBuilder.withPayload(processedComment)
                    .setHeader("status", status)
                    .build();
            log.info("[HandleComment] Sending comment after handling: {}", processedComment);
            jmsMessagingTemplate.convertAndSend(COMMENT_QUEUE_CONFIRM, processedCommentMessage);
        }
    }
}

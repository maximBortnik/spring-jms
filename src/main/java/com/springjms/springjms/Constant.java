package com.springjms.springjms;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {
    public static final String MESSAGE_QUEUE = "message.queue";
    public static final String COMMENT_QUEUE = "comment.queue";
    public static final String BOOK_QUEUE = "book.queue";
    public static final String COMMENT_QUEUE_CONFIRM = "comment.queue.confirm";
    public static final String QUEUE_ADD = "queue.added";
    public static final String QUEUE_DELETE = "queue.deleted";
}

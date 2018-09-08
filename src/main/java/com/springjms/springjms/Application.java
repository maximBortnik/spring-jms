package com.springjms.springjms;

import com.springjms.springjms.config.JmsConfig;
import com.springjms.springjms.model.Book;
import com.springjms.springjms.model.Comment;
import com.springjms.springjms.service.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import static com.springjms.springjms.Constant.BOOK_QUEUE;
import static com.springjms.springjms.Constant.COMMENT_QUEUE;
import static com.springjms.springjms.Constant.MESSAGE_QUEUE;

@SpringBootApplication
@Import(JmsConfig.class)
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		Sender sender = context.getBean(Sender.class);
		sender.send(MESSAGE_QUEUE, "Hello from activemq!", "ADD");
		sender.send(COMMENT_QUEUE, new Comment("Ivan Ivanov", "This's simple example shows how to work with activemq."), "ADD");
		sender.send(BOOK_QUEUE, new Book("Book title", "Petr Petrov" , 100), "DELETE");
	}
}
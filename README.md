# Spring JMS
_JMS (Java Message Service)_ is a Java Message Oriented Middleware used to send messages between clients.     
There are two types of messaging models in JMS:
1. Point-to-Point Messaging Domain  
Applications are built on the concept of message queues, senders, and receivers. Each message is send to a specific queue, and receiving systems consume messages from the queues established to hold their messages. Queues retain all messages sent to them until the messages are consumed by the receiver or expire. Here there is only one consumer for a message. If the receiver is not available at any point, message will remain in the message broker (Queue) and will be delivered to the consumer when it is available or free to process the message. Also receiver acknowledges the consumption on each message
2. Publish/Subscribe Messaging Domain   
Applications send message to a message broker called Topic. This topic publishes the message to all the subscribers. Topic retains the messages until it is delivered to the systems at the receiving end. Applications are loosely coupled and do not need to be on the same server. Message communications are handled by the message broker; in this case it is called a topic. A message can have multiple consumers and consumers will get the messages only after it gets subscribed and consumers need to remain active in order to get new messages.    
    
Introduction:
1. The package org.springframework.jms.core provides the core functionality for using JMS.
2. The package org.springframework.jms.support provides JMSException translation functionality.
3. The package org.springframework.jms.support.converter provides a MessageConverter abstraction to convert between Java objects and JMS messages.
4. The package org.springframework.jms.support.destination provides various strategies for managing JMS destinations, such as providing a service locator for destinations stored in JNDI.
5. The package org.springframework.jms.annotation provides the necessary infrastructure to support annotation-driven listener endpoints using @JmsListener.
6. The package org.springframework.jms.config provides the parser implementation for the jms namespace as well the java config support to configure listener containers and create listener endpoints.
7. The package org.springframework.jms.connection provides an implementation of the ConnectionFactory suitable for use in standalone applications.  
    
Main interfaces and classes:    
1. Interface _JmsOperations_ specifies a basic set of JMS operations. His implementation is _JmsTemplate_ - helper class that simplifies synchronous JMS access code.
2. Interface _JmsMessageOperations_ provides an integration with the spring messaging abstraction. This allows you to create the message to send in generic manner. His implementation is _JmsMessagingTemplate_.
3. The _JmsTemplate_ requires a reference to a _ConnectionFactory_. The _ConnectionFactory_ is part of the JMS specification and serves as the entry point for working with JMS.    
The following step to send or receive message: ConnectionFactory->Connection->Session->MessageProducer/MessageConsume->send/receive.    
Two implementations of _ConnectionFactory_ are provided _SingleConnectionFactory_ and _CachingConnectionFactory_ out of box.
4. Interface _MessageListenerContainer_ is used to receive messages from a JMS message queue. (_@JmsListener_)  
There are two standard JMS message listener containers packaged with Spring: _SimpleMessageListenerContainer_ and _DefaultMessageListenerContainer_
5. Spring provides a _JmsTransactionManager_ that manages transactions for a single JMS _ConnectionFactory_. 
This allows JMS applications to leverage the managed transaction features of Spring. 
The _JmsTransactionManager_ performs local resource transactions, binding a JMS Connection/Session pair from the specified ConnectionFactory to the thread. 
JmsTemplate automatically detects such transactional resources and operates on them accordingly.
6. Interface _MessageConverter_ strategy interface that specifies a converter between Java objects and JMS messages. 
The default implementation _SimpleMessageConverter_ supports conversion between String and TextMessage, byte[] and BytesMessage, and java.util.Map and MapMessage.
To use another message converter, you must add a specific converter to the configuration file and populate a jmsTemplate with it.
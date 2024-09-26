package com.example.logs;

@Configuration
public class AMQPConfiguration {
	@Bean
    TopicExchange logsExchange() {
        return ExchangeBuilder.topicExchange("logs.topic")
                .durable(true)
                .build();
    }
    @Bean
    Queue logsQueue() {
        return QueueBuilder.durable("logs.queue").build();
    }
    @Bean
    Binding logsBinding(final Queue logsQueue, final TopicExchange logsExchange) {
        return BindingBuilder.bind(logsQueue)
                .to(logsExchange).with("#"); // subscribe to all messages (#)
    }
접기






















}

package io.turntabl.tsrs.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class ActiveMQConfiguration {
    @Value("${activemq.broker}")
    String activeMQBroker;
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(activeMQBroker);
        return activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate template() {
        return new JmsTemplate(activeMQConnectionFactory());
    }
}
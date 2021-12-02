package io.turntabl.tsrs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;



public class MarketDataService implements MessageListener {

    Logger logger =  LoggerFactory.getLogger(MarketDataService.class);

    @Override
    public void onMessage(Message message,  byte[] pattern) {
        logger.info("Consumed Message {}", message);
    }
}


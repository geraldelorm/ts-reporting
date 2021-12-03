package io.turntabl.tsrs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;



public class MarketDataService {

    Logger logger =  LoggerFactory.getLogger(MarketDataService.class);

    public void marketDataFromExOne(String message){
        logger.info("Consumed Message From EX 1 {}", message);
    }

    public void marketDataFromExTwo(String message){
        logger.info("Consumed Message From EX 2 {}", message);
    }
}


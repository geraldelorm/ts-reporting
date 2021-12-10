package io.turntabl.tsrs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.tsrs.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class MarketDataService {

    public static List<Product> listOfMarketDataFromExchangeOne = new ArrayList<>();
    public static List<Product> listOfMarketDataFromExchangeTwo = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();

    public void marketDataFromExOne(String message) throws JsonProcessingException {
        Product[] md = objectMapper.readValue(message, Product[].class);
        listOfMarketDataFromExchangeOne = Arrays.asList(md);

        log.info("Consumed Message From Exchange 1 {}", listOfMarketDataFromExchangeOne);
    }

    public void marketDataFromExTwo(String message) throws JsonProcessingException {
        Product[] md = objectMapper.readValue(message, Product[].class);
        listOfMarketDataFromExchangeTwo = Arrays.asList(md);

        log.info("Consumed Message From Exchange 2 {}", listOfMarketDataFromExchangeTwo);
    }
}


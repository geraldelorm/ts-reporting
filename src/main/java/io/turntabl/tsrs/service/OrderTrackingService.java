package io.turntabl.tsrs.service;

import io.turntabl.tsrs.entity.Order;
import io.turntabl.tsrs.entity.OrderInfoFromExchange;
import io.turntabl.tsrs.entity.OrderStatus;
import io.turntabl.tsrs.OrderProcessing.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class OrderTrackingService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;


    private final String ExchangeKey = "c524d78f-9843-4ba2-90fc-8c7dafdad34f";

    @JmsListener(destination = "orderIDQueue")
    public void getOrderID(String message){
        log.info("Queue message received from order Processing {}",message);

        Long orderID = Long.parseLong(message);
        trackOrderStatusOnExchange(orderID);
    }

    public void trackOrderStatusOnExchange(Long orderID){
        log.info("Trying to access DB");
        Optional<Order> _order = orderRepository.findById(orderID);

        if (_order.isPresent()){
            Order order = _order.get();

            log.info("Order {}", order);
            String orderIDFromExchange = order.getOrderIdFromExchange().replaceAll("^\"+|\"+$", "");
            int exchange = order.getExchangeTradedOn();

            String exchangeUrl;
            if (exchange == 1) {
                exchangeUrl = "https://exchange.matraining.com/";
            } else {
                exchangeUrl = "https://exchange2.matraining.com/";
            }

            while (order.getStatus().equals(OrderStatus.IN_PROGRESS) || order.getStatus().equals(OrderStatus.PENDING) ){
                log.info("Order Status: " + order.getStatus());
                String orderStatusUrl = exchangeUrl + ExchangeKey + "/order/" + orderID ;

                try{
                    OrderInfoFromExchange orderInfoFromExchange = restTemplate.getForObject(orderStatusUrl, OrderInfoFromExchange.class);
                    log.info("Order Info From Exchange: " + orderInfoFromExchange);

                    int cumulativeQuantity = orderInfoFromExchange.getCumulativeQuantity();

                    if (cumulativeQuantity > 0){
                        order.setStatus(OrderStatus.IN_PROGRESS);
                        orderRepository.save(order);
                    }
                } catch (HttpServerErrorException e) {
                    order.setStatus(OrderStatus.EXECUTED);
                    orderRepository.save(order);
                    log.info("Order Status: EXECUTED" );

                    //TODO
                    // IF order is on the sell side increase account Balance
                    // IF order is on the buy side decrease account Balance
                    // Increase or Decrease product quantity in a porfolio for the user
                }
            }
        }

    }

}

package io.turntabl.tsrs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.tsrs.ClientConnectivity.entity.Portfolio;
import io.turntabl.tsrs.ClientConnectivity.entity.Product;
import io.turntabl.tsrs.ClientConnectivity.entity.User;
import io.turntabl.tsrs.ClientConnectivity.repository.PortfolioRepository;
import io.turntabl.tsrs.ClientConnectivity.repository.ProductRepository;
import io.turntabl.tsrs.ClientConnectivity.repository.UserRepository;
import io.turntabl.tsrs.OrderProcessing.entity.Order;
import io.turntabl.tsrs.OrderProcessing.entity.OrderDTO;
import io.turntabl.tsrs.OrderProcessing.entity.OrderInfoFromExchange;
import io.turntabl.tsrs.OrderProcessing.entity.OrderStatus;
import io.turntabl.tsrs.OrderProcessing.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;

@Service
@Slf4j
public class OrderTrackingService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    ProductRepository productRepository;


    private final String ExchangeKey = "457a1e4f-09ac-4421-9259-fe4d9a999577";

    @JmsListener(destination = "orderIDQueue")
    public void getOrderID(String message) throws JsonProcessingException {
        log.info("Queue message received from order Processing {}",message);

        Long orderID = Long.parseLong(message);
        trackOrderStatusOnExchange(orderID);
    }


    public void trackOrderStatusOnExchange(Long orderId) throws JsonProcessingException {
        Order order = orderRepository.findById(orderId).get();
        log.info("Order {}", order);


//        String orderID = order.getOrderIdFromExchange().replaceAll("^\"+|\"+$", "");
//
//        int exchange = order.getExchangeTradedOn();
//        String exchangeUrl;
//        if (exchange == 1) {
//            exchangeUrl = "https://exchange.matraining.com/";
//        } else {
//            exchangeUrl = "https://exchange2.matraining.com/";
//        }
//
//        while (!order.getStatus().equals(OrderStatus.EXECUTED)){
////            log.info("Order Status: " + order.getStatus());
//            String orderStatusUrl = exchangeUrl + ExchangeKey + "/order/" + orderID ;
//
//            try{
//                OrderInfoFromExchange orderInfoFromExchange = restTemplate.getForObject(orderStatusUrl, OrderInfoFromExchange.class);
////                log.info("Order Info From Exchange: " + orderInfoFromExchange);
//
//                int cumulativeQuantity = orderInfoFromExchange.getCumulativeQuantity();
//
//                if (cumulativeQuantity > 0){
//                    order.setStatus(OrderStatus.IN_PROGRESS);
//                    orderRepository.save(order);
//                }
//            } catch (HttpServerErrorException e) {
//                order.setStatus(OrderStatus.EXECUTED);
//                orderRepository.save(order);
//                log.info("Order Status: EXECUTED" );
//
//                //updating account balance
//                User user = order.getUser();
//                if (order.getSide().equals("SELL")){
//                    user.setAccount_balance(user.getAccount_balance() + (order.getPrice() * order.getQuantity()));
//                } else {
//                    user.setAccount_balance(user.getAccount_balance() - (order.getPrice() * order.getQuantity()));
//                }
//                userRepository.save(user);
//                log.info("Balance updated");
//
//                // updating product quantity for user
//                Portfolio portfolio = portfolioRepository.getById(order.getPortfolioID());
//                Product product = portfolio.getProducts().stream().filter(p -> p.getTicker().equals(order.getProduct())).findFirst().get();
//
//                if (order.getSide().equals("BUY")){
//                    product.setQuantity(product.getQuantity() + (order.getQuantity()));
//                } else {
//                    product.setQuantity(product.getQuantity() - (order.getQuantity()));
//                }
//                productRepository.save(product);
//                log.info("Product updated");
//
//            }
//        }
    }


}

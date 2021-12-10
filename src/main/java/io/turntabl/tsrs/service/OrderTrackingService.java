package io.turntabl.tsrs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderTrackingService {

    @JmsListener(destination = "orderIDQueue")
    public void getOrderID(String message){
        log.info("Queue message received from order Processing {}",message);
    }
}

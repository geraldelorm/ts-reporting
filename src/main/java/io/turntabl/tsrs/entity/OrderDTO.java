package io.turntabl.tsrs.OrderProcessing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.turntabl.tsrs.ClientConnectivity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTO {
    private Long id;

    private String product;

    private Integer quantity;

    private Double price;

    private String side ;

    private Integer exchangeTradedOn;
    private String orderIdFromExchange;

    private Enum status;

    private Date createdAt;

    private Date updatedAt;

    private User user;

    private Long portfolioID;
}
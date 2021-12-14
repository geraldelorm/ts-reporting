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
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "side")
    private String side ;

    @Column(name = "exchangeTradedOn")
    private Integer exchangeTradedOn;

    @Column(name = "order_id_exchange")
    private String orderIdFromExchange;

    @Column(name = "status")
    private Enum status;

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @JoinColumn(name = "portfolioID", nullable = false)
    private Long portfolioID;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", exchangeTradedOn=" + exchangeTradedOn +
                ", orderIdFromExchange='" + orderIdFromExchange + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                ", portfolioID=" + portfolioID +
                '}';
    }
}
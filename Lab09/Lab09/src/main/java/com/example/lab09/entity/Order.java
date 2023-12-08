package com.example.lab09.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "\"order\"")
@Component
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}

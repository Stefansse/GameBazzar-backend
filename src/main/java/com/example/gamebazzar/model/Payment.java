package com.example.gamebazzar.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @Column(nullable = false, name = "PaymentDate")
    private LocalDate paymentDate;
    @Column(nullable = false, name = "PaymentAmount")
    private Double paymentAmount;
    @Column(nullable = false, name = "PaymentStatus")
    private String paymentStatus;
    @Column(nullable = false, name ="PaymentMethod")
    private String paymentMethod;


    @ManyToOne
    private Order order;

    public Payment() {

    }
    public Payment(Order order, LocalDate paymentDate, Double paymentAmount, String paymentMethod, String paymentStatus) {
        this.order = order;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }
}

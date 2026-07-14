package org.restaurant.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.restaurant.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter @Setter
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_number", nullable = false, updatable = false, unique = true)
    private String orderNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    private Order(Builder builder){
        this.id = builder.id;
        this.orderItems = builder.orderItems;
        this.orderStatus = builder.orderStatus;
    }

    public void addItem(OrderItem orderItem){
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }

    public void removeItem(OrderItem orderItem){
        orderItem.setOrder(null);
        orderItems.remove(orderItem);
    }

    public BigDecimal getTotalPrice(){
        return orderItems.stream()
                .map(item -> item.getItemPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Integer id = null;
        private List<OrderItem> orderItems = new ArrayList<>();
        private OrderStatus orderStatus = OrderStatus.PENDING;

        private Builder(){
        }

        public Order build(){
            return new Order(this);
        }
    }
}

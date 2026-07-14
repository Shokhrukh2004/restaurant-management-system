package org.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.restaurant.entity.enums.OrderItemStatus;
import org.restaurant.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "order_items")
@Getter @Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(nullable = false, name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "item_price", nullable = false, precision = 10, scale = 2, updatable = false)
    private BigDecimal itemPrice;

    @Column(name = "special_requests")
    private String specialRequests;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_item_status", nullable = false)
    private OrderItemStatus orderItemStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private OrderItem(Builder builder){
        this.id = builder.id;
        this.menuItem = builder.menuItem;
        this.itemPrice = builder.itemPrice;
        this.quantity = builder.quantity;
        this.specialRequests = builder.specialRequests;
        this.orderItemStatus = builder.orderItemStatus;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Integer id = null;
        private MenuItem menuItem;
        private BigDecimal itemPrice;
        private int quantity;
        private String specialRequests;
        private OrderItemStatus orderItemStatus = OrderItemStatus.PENDING;

        private Builder(){
        }

        public Builder menuItem(MenuItem menuItem){
            this.menuItem = menuItem;
            return this;
        }

        public Builder itemPrice(BigDecimal itemPrice){
            this.itemPrice = itemPrice;
            return this;
        }

        public Builder quantity(int quantity){
            this.quantity = quantity;
            return this;
        }

        public Builder specialRequests(String specialRequests){
            this.specialRequests = specialRequests;
            return this;
        }

        public OrderItem build(){
            return new OrderItem(this);
        }
    }
}

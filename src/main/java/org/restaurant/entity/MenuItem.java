package org.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.restaurant.entity.enums.MenuCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu_items")
@Getter @Setter
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column(nullable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuCategory category;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private MenuItem(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.category = builder.category;
        this.isAvailable = builder.isAvailable;
        this.isDeleted = builder.isDeleted;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int id = 0;
        private String name;
        private String description;
        private BigDecimal price;
        private MenuCategory category;
        private boolean isAvailable = true;
        private boolean isDeleted = false;

        public Builder(){

        }

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder price(BigDecimal price){
            this.price = price;
            return this;
        }

        public Builder category(MenuCategory category){
            this.category = category;
            return this;
        }

        public Builder isAvailable(boolean isAvailable){
            this.isAvailable = isAvailable;
            return this;
        }

        public Builder isDeleted(boolean isDeleted){
            this.isDeleted = isDeleted;
            return this;
        }

        public MenuItem build() {
            return new MenuItem(this);
        }
    }
}

package org.restaurant.dto.menuItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.restaurant.entity.enums.MenuCategory;

import java.math.BigDecimal;

@Getter
public class MenuItemResponse{
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final MenuCategory category;
    private final boolean isAvailable;

    private MenuItemResponse(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.category = builder.category;
        this.isAvailable = builder.isAvailable;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String name;
        private String description;
        private BigDecimal price;
        private MenuCategory category;
        private boolean isAvailable;

        public Builder(){
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

        public MenuItemResponse build(){
            return new MenuItemResponse(this);
        }
    }

}

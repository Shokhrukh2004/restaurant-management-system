package org.restaurant.shared.util.order;

import org.restaurant.feature.menu.entity.MenuItem;
import org.restaurant.feature.menu.repository.MenuItemRepository;
import org.restaurant.feature.order.entity.Order;
import org.restaurant.feature.order.entity.OrderItem;
import org.restaurant.feature.order.repository.OrderItemRepository;
import org.restaurant.feature.order.repository.OrderRepository;
import org.restaurant.shared.exception.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EntityFinder {
    private final MenuItemRepository menuItemRepo;
    private final OrderItemRepository orderItemRepo;
    private final OrderRepository orderRepo;

    public EntityFinder(MenuItemRepository menuRepo,
                        OrderItemRepository orderItemRepo,
                        OrderRepository orderRepo) {
        this.menuItemRepo = menuRepo;
        this.orderItemRepo = orderItemRepo;
        this.orderRepo = orderRepo;
    }

    public MenuItem getMenuItemIfExist(int menuItemId){

        return menuItemRepo
                .findByIdAndIsDeletedFalse(menuItemId)
                .orElseThrow(
                        () -> new NotFoundException("Menu Item not found with ID " + menuItemId));
    }

    public Order getOrderIfExists(int orderId){

        return orderRepo
                .findById(orderId)
                .orElseThrow(
                        () -> new NotFoundException("Order not found with id " + orderId));
    }

    public OrderItem getOrderItemIfExists(int itemId){
        return orderItemRepo
                .findById(itemId)
                .orElseThrow(() -> new NotFoundException("Order item with id "
                        + itemId + " not found."));

    }

}

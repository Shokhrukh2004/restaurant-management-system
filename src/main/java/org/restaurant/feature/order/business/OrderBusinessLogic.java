package org.restaurant.feature.order.business;

import org.restaurant.feature.menu.entity.MenuItem;
import org.restaurant.feature.order.enums.OrderStatus;
import org.restaurant.shared.exception.ConflictException;
import org.restaurant.feature.menu.repository.MenuItemRepository;
import org.restaurant.feature.order.repository.OrderItemRepository;
import org.restaurant.feature.order.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderBusinessLogic {
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final MenuItemRepository menuItemRepo;

    public OrderBusinessLogic(OrderRepository orderRepo,
                              OrderItemRepository orderItemRepo,
                              MenuItemRepository menuItemRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.menuItemRepo = menuItemRepo;
    }

    public void menuItemAvailabilityCheck(MenuItem menuItem){
        if(!menuItem.isAvailable()){
            throw new ConflictException("Menu Item is not available for now");
        }
    }

    public void orderStatusUpdateCheck(OrderStatus current, OrderStatus updated){
        if(!current.canTransition(updated)){
            throw new ConflictException("Invalid Status Transition: Cannot transition to "
                    + updated + " status from " + current + " status.");
        }
    }
}

package org.restaurant.feature.order.business;

import org.restaurant.feature.menu.entity.MenuItem;
import org.restaurant.feature.menu.message.MenuItemErrorMessage;
import org.restaurant.feature.order.enums.OrderStatus;
import org.restaurant.shared.exception.ConflictException;
import org.restaurant.shared.message.CommonErrorMessage;
import org.springframework.stereotype.Component;

@Component
public class OrderBusinessLogic {

    public OrderBusinessLogic() {
    }

    public void menuItemAvailabilityCheck(MenuItem menuItem){
        if(!menuItem.isAvailable()){
            throw new ConflictException(MenuItemErrorMessage
                    .MENU_ITEM_UNAVAILABLE
                    .formatted(menuItem.getId()));
        }
    }

    public void orderStatusUpdateCheck(OrderStatus current, OrderStatus updated){
        if(!current.canTransition(updated)){
            throw new ConflictException(CommonErrorMessage
                    .INVALID_STATUS_TRANSITION
                    .formatted(current, updated));
        }
    }

}

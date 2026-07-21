package org.restaurant.feature.menu.business;

import org.restaurant.feature.menu.entity.MenuItem;
import org.restaurant.feature.menu.message.MenuItemErrorMessage;
import org.restaurant.shared.exception.ConflictException;
import org.restaurant.feature.menu.repository.MenuItemRepository;
import org.restaurant.shared.message.CommonErrorMessage;
import org.springframework.stereotype.Component;

@Component
public class MenuItemBusinessLogic {
    private final MenuItemRepository menuRepo;

    public MenuItemBusinessLogic(MenuItemRepository menuRepo) {
        this.menuRepo = menuRepo;
    }

    public void nameDuplicateCheck(String name){
        menuRepo.findByNameIgnoreCaseAndIsDeletedFalse(name)
                .ifPresent(menu -> {
            throw new ConflictException(CommonErrorMessage
                    .DUPLICATE_ENTRY
                    .formatted("Menu item", name));
        });
    }

    public void nameDuplicateCheck(String oldName, String newName){
        if(oldName.equals(newName)) {
            return;
        }

        nameDuplicateCheck(newName);
    }

    public void availabilityCheck(MenuItem item, boolean isAvailable){
        String response = isAvailable ? "Available" : "Unavailable";
        if(item.isAvailable() == isAvailable) {
            throw new ConflictException(MenuItemErrorMessage
                    .MENU_ITEM_ALREADY_AVAILABILITY
                    .formatted(response, item.getId()));
        }
    }
}

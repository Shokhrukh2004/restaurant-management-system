package org.restaurant.feature.menu.business;

import org.restaurant.feature.menu.entity.MenuItem;
import org.restaurant.shared.exception.ConflictException;
import org.restaurant.feature.menu.repository.MenuItemRepository;
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
            throw new ConflictException("Name: " + name +" already exists");
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
            throw new ConflictException("Item is already set to " + response);
        }
    }
}

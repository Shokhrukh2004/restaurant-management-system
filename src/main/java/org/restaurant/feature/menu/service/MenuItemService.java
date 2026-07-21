package org.restaurant.feature.menu.service;

import org.restaurant.feature.menu.dto.MenuItemCreateRequest;

import org.restaurant.feature.menu.parser.MenuItemParser;
import org.restaurant.feature.menu.dto.MenuItemResponse;
import org.restaurant.feature.menu.dto.MenuItemUpdateRequest;
import org.restaurant.feature.menu.entity.MenuItem;
import org.restaurant.feature.menu.enums.MenuCategory;
import org.restaurant.feature.menu.repository.MenuItemRepository;
import org.restaurant.feature.menu.business.MenuItemBusinessLogic;
import org.restaurant.shared.util.order.EntityFinder;
import org.restaurant.shared.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.restaurant.feature.menu.parser.MenuItemParser.toMenuItemFromCreateRequest;
import static org.restaurant.feature.menu.parser.MenuItemParser.toResponseFromMenuItem;


@Service
@Transactional
public class MenuItemService {

    private final MenuItemRepository menuRepo;
    private final MenuItemBusinessLogic logic;
    private final EntityFinder entityFinder;

    public MenuItemService(MenuItemRepository menuRepo,
                           MenuItemBusinessLogic logic,
                           EntityFinder entityFinder) {
        this.menuRepo = menuRepo;
        this.logic = logic;
        this.entityFinder = entityFinder;
    }

    public MenuItemResponse findById(int id){
        Validator.validatePositiveInt(id, "Id");

        return toResponseFromMenuItem(entityFinder.getMenuItemIfExist(id));
    }

    public List<MenuItemResponse> findAll(){
        List<MenuItem> items = menuRepo.findByIsDeletedFalse();

        return items.stream()
                .map(MenuItemParser::toResponseFromMenuItem)
                .toList();
    }

    public List<MenuItemResponse> findByCategory(MenuCategory category){
        List<MenuItem> items = menuRepo.findByCategoryAndIsDeletedFalse(category);

        return items.stream()
                .map(MenuItemParser::toResponseFromMenuItem)
                .toList();
    }

    public List<MenuItemResponse> findByName(String name){
        Validator.validateString(name, "Name");

        return menuRepo
                .findByNameContainingIgnoreCaseAndIsDeletedFalse(name)
                .stream()
                .map(MenuItemParser::toResponseFromMenuItem)
                .toList();
    }

    public MenuItemResponse createMenuItem(MenuItemCreateRequest request){
        logic.nameDuplicateCheck(request.getName());

        MenuItem menuItem = toMenuItemFromCreateRequest(request);

        menuRepo.save(menuItem);

        return toResponseFromMenuItem(menuItem);
    }

    public MenuItemResponse updateMenuItem(MenuItemUpdateRequest request){
        MenuItem item = entityFinder.getMenuItemIfExist(request.getId());

        logic.nameDuplicateCheck(item.getName(), request.getName());

        updateItem(request, item);

        menuRepo.save(item);
        return toResponseFromMenuItem(item);
    }


    public MenuItemResponse updateAvailability(int id, boolean isAvailable){
        Validator.validatePositiveInt(id, "Menu Item Id");

        MenuItem item = entityFinder.getMenuItemIfExist(id);

        logic.availabilityCheck(item, isAvailable);

        item.setAvailable(isAvailable);

        MenuItem updated = menuRepo.save(item);

        return toResponseFromMenuItem(updated);
    }

    public void deleteMenuItem(int id){
        Validator.validatePositiveInt(id, "Menu Item Id");

        MenuItem item = entityFinder.getMenuItemIfExist(id);

        item.setDeleted(true);

        menuRepo.save(item);
    }

    private void updateItem(MenuItemUpdateRequest request, MenuItem item){
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setCategory(request.getMenuCategory());
    }
}

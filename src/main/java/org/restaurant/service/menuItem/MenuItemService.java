package org.restaurant.service.menuItem;

import org.restaurant.dto.menuItem.MenuItemCreateRequest;

import org.restaurant.parser.MenuItemParser;
import org.restaurant.dto.menuItem.MenuItemResponse;
import org.restaurant.dto.menuItem.MenuItemUpdateRequest;
import org.restaurant.entity.MenuItem;
import org.restaurant.entity.enums.MenuCategory;
import org.restaurant.exception.NotFoundException;
import org.restaurant.repository.MenuItemRepository;
import org.restaurant.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.restaurant.parser.MenuItemParser.toMenuItemFromCreateRequest;
import static org.restaurant.parser.MenuItemParser.toResponseFromMenuItem;


@Service
@Transactional
public class MenuItemService {

    private final MenuItemRepository menuRepo;
    private final MenuItemBusinessLogic logic;

    public MenuItemService(MenuItemRepository menuRepo,
                           MenuItemBusinessLogic logic) {
        this.menuRepo = menuRepo;
        this.logic = logic;
    }

    public MenuItemResponse findById(int id){
        Validator.validatePositiveInt(id, "Id");

        return toResponseFromMenuItem(getMenuItemIfExists(id));
    }

    public List<MenuItemResponse> findAll(){
        List<MenuItem> items = menuRepo.findByIsDeletedFalse();

        return items.stream()
                .map(MenuItemParser::toResponseFromMenuItem)
                .toList();
    }

    public List<MenuItemResponse> findByCategory(MenuCategory category){
        List<MenuItem> items = menuRepo.findByMenuCategoryAndIsDeletedFalse(category);

        return items.stream()
                .map(MenuItemParser::toResponseFromMenuItem)
                .toList();
    }

    public List<MenuItemResponse> findByName(String name){
        Validator.validateString(name, "Name");

        return menuRepo
                .findByNameContainingAndIgnoreCaseAndIsDeletedFalse(name)
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
        MenuItem item = getMenuItemIfExists(request.getId());

        logic.nameDuplicateCheck(item.getName(), request.getName());

        updateItem(request, item);

        menuRepo.save(item);
        return toResponseFromMenuItem(item);
    }


    public void updateAvailability(int id, boolean isAvailable){
        MenuItem item = getMenuItemIfExists(id);

        logic.availabilityCheck(item, isAvailable);

        item.setAvailable(isAvailable);

        menuRepo.save(item);
    }

    public void deleteMenuItem(int id){
        MenuItem item = getMenuItemIfExists(id);

        item.setDeleted(true);

        menuRepo.save(item);
    }



    private MenuItem getMenuItemIfExists(int itemId){
        return menuRepo.findByIdAndIsDeletedFalse(itemId).
                orElseThrow(() -> new NotFoundException("Menu Item with Id " + itemId + " not found"));
    }

    private void updateItem(MenuItemUpdateRequest request, MenuItem item){
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setCategory(request.getMenuCategory());
    }
}

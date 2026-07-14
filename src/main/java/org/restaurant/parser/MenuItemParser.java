package org.restaurant.parser;

import org.restaurant.dto.menuItem.MenuItemCreateRequest;
import org.restaurant.dto.menuItem.MenuItemResponse;
import org.restaurant.entity.MenuItem;

public class MenuItemParser {

    public static MenuItem toMenuItemFromCreateRequest(MenuItemCreateRequest request){
        return MenuItem.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getMenuCategory())
                .build();
    }

    public static MenuItemResponse toResponseFromMenuItem(MenuItem item){
        return MenuItemResponse.builder()
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .category(item.getCategory())
                .isAvailable(item.isAvailable())
                .build();
    }
}

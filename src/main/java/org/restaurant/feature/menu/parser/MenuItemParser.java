package org.restaurant.feature.menu.parser;

import org.restaurant.feature.menu.dto.MenuItemCreateRequest;
import org.restaurant.feature.menu.dto.MenuItemResponse;
import org.restaurant.feature.menu.entity.MenuItem;

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
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .category(item.getCategory())
                .isAvailable(item.isAvailable())
                .build();
    }
}

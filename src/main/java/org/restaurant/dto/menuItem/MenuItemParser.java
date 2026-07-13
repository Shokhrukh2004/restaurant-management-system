package org.restaurant.dto.menuItem;

import org.restaurant.entity.MenuItem;

public class MenuItemParser {

    public static MenuItem toMenuItemFromCreateRequest(MenuItemRequest request){
        return new MenuItem(
                0,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getMenuCategory(),
                true,
                false,
                null,
                null
        );
    }

    public static MenuItemResponse toResponseFromMenuItem(MenuItem item){
        return new MenuItemResponse(
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getCategory(),
                item.isAvailable()
        );
    }
}

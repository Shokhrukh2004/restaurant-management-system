package org.restaurant.controller.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.restaurant.feature.menu.dto.MenuItemCreateRequest;
import org.restaurant.feature.menu.dto.MenuItemResponse;
import org.restaurant.feature.menu.dto.MenuItemUpdateRequest;
import org.restaurant.feature.menu.enums.MenuCategory;
import org.restaurant.feature.menu.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@Tag(name = "Menu Items", description = "Menu item management endpoints")
public class MenuItemController {

    private final MenuItemService menuService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuService = menuItemService;
    }

    @PostMapping
    @Operation(summary = "Create new menu item", description = "Create new menu item in the system")
    @ApiResponse(responseCode = "201", description = "Menu item created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "409", description = "Menu item already exists")
    public ResponseEntity<MenuItemResponse> save(
            @Valid
            @RequestBody MenuItemCreateRequest menuItem) {

        MenuItemResponse item = menuService.createMenuItem(menuItem);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(item);
    }

    @PutMapping
    @Operation(summary = "Update menu item", description = "Update an existing item")
    @ApiResponse(responseCode = "200", description = "Menu item updated successfully")
    @ApiResponse(responseCode = "404", description = "Menu item not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<MenuItemResponse> update(
            @Valid
            @RequestBody MenuItemUpdateRequest menuItem) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.updateMenuItem(menuItem));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update availability", description = "Toggle menu item availability")
    @ApiResponse(responseCode = "200", description = "Availability updated")
    @ApiResponse(responseCode = "404", description = "Menu item not found")
    public ResponseEntity<MenuItemResponse> updateAvailability(
            @PathVariable int id,
            @RequestParam boolean availability) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService
                        .updateAvailability(id, availability));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete menu item", description = "Soft delete a menu item")
    @ApiResponse(responseCode = "204", description = "Menu item deleted")
    @ApiResponse(responseCode = "404", description = "Menu item not found")
    public ResponseEntity<Void> delete(@PathVariable int id){

        menuService.deleteMenuItem(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get menu item by ID", description = "Retrieve a specific menu item by ID")
    @ApiResponse(responseCode = "200", description = "Menu item found and returned")
    @ApiResponse(responseCode = "404", description = "Menu item not found")
    public ResponseEntity<MenuItemResponse> findById(@PathVariable int id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Get all menu items", description = "Retrieve all menu items")
    @ApiResponse(responseCode = "200", description = "List of menu items returned")
    public ResponseEntity<List<MenuItemResponse>> findAll(){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.findAll());
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get menu item by name", description = "Retrieve menu item by name")
    @ApiResponse(responseCode = "200", description = "List of matching menu items by given name")
    public ResponseEntity<List<MenuItemResponse>> findByName(@PathVariable String name){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.findByName(name));
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get menu items by category", description = "Get all menu items in a specific category")
    @ApiResponse(responseCode = "200", description = "List of menu items in category")
    public ResponseEntity<List<MenuItemResponse>> findByCategory(
            @PathVariable MenuCategory category){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.findByCategory(category));
    }


}

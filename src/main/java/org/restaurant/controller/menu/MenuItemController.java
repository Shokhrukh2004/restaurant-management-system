package org.restaurant.controller.menu;

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
public class MenuItemController {

    private final MenuItemService menuService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuService = menuItemService;
    }

    @PostMapping
    public ResponseEntity<MenuItemResponse> save(
            @Valid
            @RequestBody MenuItemCreateRequest menuItem) {

        MenuItemResponse item = menuService.createMenuItem(menuItem);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(item);
    }

    @PutMapping
    public ResponseEntity<MenuItemResponse> update(
            @Valid
            @RequestBody MenuItemUpdateRequest menuItem) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.updateMenuItem(menuItem));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MenuItemResponse> updateAvailability(
            @PathVariable int id,
            @RequestParam boolean availability) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService
                        .updateAvailability(id, availability));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){

        menuService.deleteMenuItem(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> findById(@PathVariable int id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MenuItemResponse>> findAll(){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<MenuItemResponse>> findByName(@PathVariable String name){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.findByName(name));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItemResponse>> findByCategory(
            @PathVariable MenuCategory category){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.findByCategory(category));
    }


}

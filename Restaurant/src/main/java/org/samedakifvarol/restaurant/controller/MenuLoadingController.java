package org.samedakifvarol.restaurant.controller;

import lombok.AllArgsConstructor;
import org.samedakifvarol.restaurant.controller.request.MenuRequest;
import org.samedakifvarol.restaurant.controller.response.MenuResponse;
import org.samedakifvarol.restaurant.model.dto.MenuDto;
import org.samedakifvarol.restaurant.model.dto.MenuItemDto;
import org.samedakifvarol.restaurant.model.entity.MenuItemEntity;
import org.samedakifvarol.restaurant.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuLoadingController {

    private MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuRequest menuRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.add(menuRequest));
    }

    @GetMapping
    public ResponseEntity<Page<MenuDto>> getMenus(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(menuService.getMenus(page));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(menuService.getMenu(id));
    }
}

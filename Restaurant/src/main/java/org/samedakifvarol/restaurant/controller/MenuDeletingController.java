package org.samedakifvarol.restaurant.controller;

import lombok.AllArgsConstructor;
import org.samedakifvarol.restaurant.repository.MenuRepository;
import org.samedakifvarol.restaurant.service.MenuService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuDeletingController {

    private MenuService menuService;

    @DeleteMapping(path = "/menu/{id}")
    public void deleteMenu(@PathVariable("id") Long id){
        menuService.delete(id);
    }
}

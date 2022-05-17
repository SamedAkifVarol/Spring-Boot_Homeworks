package org.samedakifvarol.restaurant.controller;

import lombok.AllArgsConstructor;
import org.samedakifvarol.restaurant.controller.request.MenuRequest;
import org.samedakifvarol.restaurant.controller.response.MenuResponse;
import org.samedakifvarol.restaurant.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuUpdatingController {
    private MenuService menuService;

    @PutMapping("/{id}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable("id") Long id,
                                                   @Valid @RequestBody MenuRequest menuRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(menuService.update(menuRequest,id));
    }
}

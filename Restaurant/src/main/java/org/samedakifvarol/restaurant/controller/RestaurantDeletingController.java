package org.samedakifvarol.restaurant.controller;

import lombok.AllArgsConstructor;
import org.samedakifvarol.restaurant.service.RestaurantService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class RestaurantDeletingController {
    private RestaurantService restaurantService;

    @DeleteMapping(path = "/{id}")
    public void deleteRestaurant(@PathVariable("id") Long id){
        restaurantService.delete(id);
    }
}

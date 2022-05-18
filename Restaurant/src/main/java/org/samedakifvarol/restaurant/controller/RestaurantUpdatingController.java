package org.samedakifvarol.restaurant.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.samedakifvarol.restaurant.controller.response.UpdateRestaurant;
import org.samedakifvarol.restaurant.model.dto.RestaurantDto;
import org.samedakifvarol.restaurant.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class RestaurantUpdatingController {

    private RestaurantService restaurantService;

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateRestaurant> updateRestaurant(@PathVariable("id") Long id,
                                                   @Valid @RequestBody UpdateRestaurant restaurantDetails) {
        return  ResponseEntity.status(HttpStatus.OK).body(restaurantService.update(restaurantDetails,id));
    }
}

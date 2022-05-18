package org.samedakifvarol.restaurant.controller;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.samedakifvarol.restaurant.controller.request.AddRestaurantRequest;
import org.samedakifvarol.restaurant.controller.response.AddRestaurantResponse;
import org.samedakifvarol.restaurant.controller.response.GetRestaurantResponse;
import org.samedakifvarol.restaurant.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class RestaurantController {

    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<AddRestaurantResponse> add(@Valid @RequestBody AddRestaurantRequest restaurantDetails){
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.add(restaurantDetails));
    }

    @GetMapping
    public ResponseEntity<Page<GetRestaurantResponse>> gets(Pageable page) {
        return ResponseEntity.status(HttpStatus.FOUND).body(restaurantService.gets(page));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GetRestaurantResponse> getRestaurant(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(restaurantService.getRestaurant(id));
    }

}

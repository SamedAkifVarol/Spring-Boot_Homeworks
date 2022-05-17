package org.samedakifvarol.restaurant.controller;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.samedakifvarol.restaurant.controller.request.AddRestaurantRequest;
import org.samedakifvarol.restaurant.controller.response.AddRestaurantResponse;
import org.samedakifvarol.restaurant.controller.response.GetRestaurantResponse;
import org.samedakifvarol.restaurant.controller.response.UpdateRestaurant;
import org.samedakifvarol.restaurant.service.RestaurantService;
import org.samedakifvarol.restaurant.model.dto.RestaurantDto;
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

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateRestaurant> update(@PathVariable("id") Long id,
                                                   @Valid @RequestBody UpdateRestaurant restaurantDetails) {
        RestaurantMenuDto restaurantMenuDto = restaurantService.update(restaurantDetails,id);
        UpdateRestaurant returnValue = new ModelMapper().map(restaurantMenuDto, UpdateRestaurant.class);
        return  ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping
    public ResponseEntity<Page<GetRestaurantResponse>> gets(Pageable page) {
                Page<GetRestaurantResponse> restaurants = restaurantService.gets(page);
                //.stream()
                //.map(get -> new ModelMapper().map(get,GetRestaurantResponse.class)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.FOUND).body(restaurants);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GetRestaurantResponse> getRestaurant(@PathVariable("id") Long id){
        RestaurantDto restaurantDto = restaurantService.getRestaurant(id);
        GetRestaurantResponse returnValue = new ModelMapper().map(restaurantDto, GetRestaurantResponse.class);
        return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);
    }

}

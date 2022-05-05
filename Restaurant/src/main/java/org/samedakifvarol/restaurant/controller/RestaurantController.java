package org.samedakifvarol.restaurant.controller;

import lombok.AllArgsConstructor;

import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.samedakifvarol.restaurant.data.RestaurantEntity;
import org.samedakifvarol.restaurant.data.RestaurantRepository;
import org.samedakifvarol.restaurant.model.*;
import org.samedakifvarol.restaurant.service.RestaurantService;
import org.samedakifvarol.restaurant.shared.RestaurantDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class RestaurantController {

    RestaurantService restaurantService;
    RestaurantRepository restaurantRepository;

    @PostMapping
    public ResponseEntity<AddRestaurantResponse> add(@Valid @RequestBody AddRestaurantRequest restaurantDetails){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RestaurantDto restaurantDto = modelMapper.map(restaurantDetails,RestaurantDto.class);
        RestaurantDto addedRestaurant = restaurantService.add(restaurantDto);

        AddRestaurantResponse returnValue =modelMapper.map(addedRestaurant, AddRestaurantResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateRestaurant> update(@PathVariable("id") Long id,
                                                   @Valid @RequestBody UpdateRestaurant restaurantDetails) {
        RestaurantDto restaurantDto = restaurantService.update(restaurantDetails,id);
        UpdateRestaurant returnValue = new ModelMapper().map(restaurantDto, UpdateRestaurant.class);
        return  ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping
    public ResponseEntity<GetRestaurantResponse> gets() {
        RestaurantDto restaurantDto = restaurantService.gets();
        GetRestaurantResponse returnValue = new ModelMapper().map(restaurantDto, GetRestaurantResponse.class);
        return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GetRestaurantResponse> getRestaurant(@PathVariable("id") Long id){
        RestaurantDto restaurantDto = restaurantService.getRestaurant(id);
        GetRestaurantResponse returnValue = new ModelMapper().map(restaurantDto, GetRestaurantResponse.class);
        return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        restaurantService.delete(id);
    }

}

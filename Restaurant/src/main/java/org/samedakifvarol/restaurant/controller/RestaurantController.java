package org.samedakifvarol.restaurant.controller;

import lombok.AllArgsConstructor;

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
    public ResponseEntity<AddRestaurantResponseModel> add(@Valid @RequestBody AddRestaurantRequestModel restaurantDetails){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RestaurantDto restaurantDto = modelMapper.map(restaurantDetails,RestaurantDto.class);
        RestaurantDto addedRestaurant = restaurantService.add(restaurantDto);

        AddRestaurantResponseModel returnValue =modelMapper.map(addedRestaurant,AddRestaurantResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateRestaurantModel> update(@PathVariable("id") Long id,
                                                        @Valid @RequestBody UpdateRestaurantModel restaurantDetails) {
        RestaurantDto restaurantDto = restaurantService.update(restaurantDetails,id);
        UpdateRestaurantModel returnValue = new ModelMapper().map(restaurantDto,UpdateRestaurantModel.class);
        return  ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping
    public List<RestaurantEntity> gets() {
        return restaurantService.gets();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GetRestaurantResponseModel> getRestaurant(@PathVariable("id") Long id){
        RestaurantDto restaurantDto = restaurantService.getRestaurant(id);
        GetRestaurantResponseModel returnValue = new ModelMapper().map(restaurantDto,GetRestaurantResponseModel.class);
        return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id){
        restaurantService.delete(id);
    }

}

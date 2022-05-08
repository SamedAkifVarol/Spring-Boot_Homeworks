package org.samedakifvarol.restaurant.controller;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.samedakifvarol.restaurant.data.MenuRepository;
import org.samedakifvarol.restaurant.data.RestaurantEntity;
import org.samedakifvarol.restaurant.data.RestaurantRepository;
import org.samedakifvarol.restaurant.model.*;
import org.samedakifvarol.restaurant.service.MenuService;
import org.samedakifvarol.restaurant.service.RestaurantService;
import org.samedakifvarol.restaurant.shared.MenuDto;
import org.samedakifvarol.restaurant.shared.RestaurantDto;
import org.samedakifvarol.restaurant.shared.RestaurantMenuDto;
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

    RestaurantService restaurantService;
    RestaurantRepository restaurantRepository;
    MenuService menuService;
    MenuRepository menuRepository;

    // ------------------------------------------ MENU ISLEMLERI --------------------------------------------------

    // Menu Ekle -----------------------------------------------
    @PostMapping("/menu")
    public ResponseEntity<MenuResponse> add(@RequestBody MenuRequest addMenuRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MenuDto menuDto = modelMapper.map(addMenuRequest,MenuDto.class);
        MenuDto addedMenu = menuService.add(menuDto);

        MenuResponse returnValue = modelMapper.map(addedMenu, MenuResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    //Menu Getir -------------------------------------------------------------------------------------------------------
    @GetMapping(path = "/menu/{id}")
    public ResponseEntity<MenuResponse> getMenu (@PathVariable("id") Long id){
        MenuDto menuDto = menuService.getMenu(id);
        MenuResponse returnValue = new ModelMapper().map(menuDto, MenuResponse.class);
        return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);
    }
    //Menu Guncelle ----------------------------------------------------------------------------------------------------
    @PutMapping("/menu/{id}")
    public ResponseEntity<MenuResponse> update(@PathVariable("id") Long id,
                                               @Valid @RequestBody MenuRequest menuRequest) {
        MenuDto menuDto = menuService.update(menuRequest,id);
        MenuResponse returnValue = new ModelMapper().map(menuDto, MenuResponse.class);
        return  ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
    //Menu Sil ---------------------------------------------------------------------
    @DeleteMapping(path = "/menu/{id}")
    public void deleteMenu(@PathVariable("id") Long id){
        menuRepository.deleteById(id);
    }

    // ------------------------------------------ RESTORANT ISLEMLERI --------------------------------------------------

    // Restaurant Ekle -----------------------------------------------
    @PostMapping
    public ResponseEntity<AddRestaurantResponse> add(@Valid @RequestBody AddRestaurantRequest restaurantDetails){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RestaurantMenuDto restaurantMenuDto = modelMapper.map(restaurantDetails,RestaurantMenuDto.class);
        RestaurantMenuDto addedRestaurant = restaurantService.add(restaurantMenuDto, restaurantDetails.getMenu_id());

        AddRestaurantResponse returnValue =modelMapper.map(addedRestaurant, AddRestaurantResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    //Restaurant Güncelle -----------------------------------------------
    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateRestaurant> update(@PathVariable("id") Long id,
                                                   @Valid @RequestBody UpdateRestaurant restaurantDetails) {
        RestaurantMenuDto restaurantMenuDto = restaurantService.update(restaurantDetails,id);
        UpdateRestaurant returnValue = new ModelMapper().map(restaurantMenuDto, UpdateRestaurant.class);
        return  ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    // Restorantları Getir -----------------------------------------------
    @GetMapping
    public ResponseEntity<Page<RestaurantEntity>> gets(Pageable page) {
        Page<RestaurantEntity> restaurants =  restaurantService.gets(page);
        return ResponseEntity.status(HttpStatus.FOUND).body(restaurants);
    }

    //Restaurant getir -------------------------------------------
    @GetMapping(path = "/{id}")
    public ResponseEntity<GetRestaurantResponse> getRestaurant(@PathVariable("id") Long id){
        RestaurantDto restaurantDto = restaurantService.getRestaurant(id);
        GetRestaurantResponse returnValue = new ModelMapper().map(restaurantDto, GetRestaurantResponse.class);
        return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);
    }

    //Restaurant Sil ------------------------------------------------------------------
    @DeleteMapping(path = "/{id}")
    public void deleteRestaurant(@PathVariable("id") Long id){
        restaurantService.delete(id);
    }

}

package org.samedakifvarol.restaurant.service;

import org.samedakifvarol.restaurant.data.RestaurantEntity;
import org.samedakifvarol.restaurant.model.GetRestaurantResponse;
import org.samedakifvarol.restaurant.model.UpdateRestaurant;
import org.samedakifvarol.restaurant.shared.RestaurantDto;
import org.samedakifvarol.restaurant.shared.RestaurantMenuDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface RestaurantService extends UserDetailsService {
    RestaurantMenuDto add(RestaurantMenuDto restaurantDetails, Long id);
    RestaurantMenuDto update(UpdateRestaurant restaurantDetails , Long id);
    Page<RestaurantEntity> gets(Pageable page);
    RestaurantDto getRestaurant(Long id);
    void delete(Long id);
    RestaurantDto getRestaurantDetailsbyId(String Id);

}

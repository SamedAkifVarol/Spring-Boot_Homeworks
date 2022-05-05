package org.samedakifvarol.restaurant.service;

import org.samedakifvarol.restaurant.data.RestaurantEntity;
import org.samedakifvarol.restaurant.model.UpdateRestaurant;
import org.samedakifvarol.restaurant.shared.RestaurantDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface RestaurantService extends UserDetailsService {
    RestaurantDto add(RestaurantDto restaurantDetails);
    RestaurantDto update(UpdateRestaurant restaurantDetails , Long id);
    RestaurantDto gets();
    RestaurantDto getRestaurant(Long id);
    void delete(Long id);
    RestaurantDto getRestaurantDetailsbyId(String Id);
}

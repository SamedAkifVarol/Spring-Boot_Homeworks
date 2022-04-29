package org.samedakifvarol.restaurant.service;

import org.samedakifvarol.restaurant.data.RestaurantEntity;
import org.samedakifvarol.restaurant.model.UpdateRestaurantModel;
import org.samedakifvarol.restaurant.shared.RestaurantDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface RestaurantService extends UserDetailsService {
    RestaurantDto add(RestaurantDto restaurantDetails);
    RestaurantDto update(Long id);
    List<RestaurantEntity> gets();
    RestaurantDto getRestaurant(Long id);
    void delete(Long id);
}

package org.samedakifvarol.restaurant.service;

import org.samedakifvarol.restaurant.controller.request.AddRestaurantRequest;
import org.samedakifvarol.restaurant.controller.response.AddRestaurantResponse;
import org.samedakifvarol.restaurant.controller.response.GetRestaurantResponse;
import org.samedakifvarol.restaurant.controller.response.UpdateRestaurant;
import org.samedakifvarol.restaurant.model.dto.RestaurantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface RestaurantService extends UserDetailsService {
    AddRestaurantResponse add(AddRestaurantRequest addRestaurantRequest);
    RestaurantMenuDto update(UpdateRestaurant restaurantDetails , Long id);
    Page<GetRestaurantResponse> gets(Pageable page);
    RestaurantDto getRestaurant(Long id);
    void delete(Long id);
    RestaurantDto getRestaurantDetailsbyId(String Id);

}

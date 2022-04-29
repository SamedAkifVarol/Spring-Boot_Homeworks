package org.samedakifvarol.restaurant.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<RestaurantEntity,Long> {
    RestaurantEntity findByRestaurantId(String restaurantId);
}

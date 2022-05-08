package org.samedakifvarol.restaurant.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<RestaurantEntity,Long> {
    RestaurantEntity findByRestaurantId(String restaurantId);

    Page<RestaurantEntity> findAll(Pageable page);
}

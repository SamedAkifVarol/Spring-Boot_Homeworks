package org.samedakifvarol.restaurant.repository;

import org.samedakifvarol.restaurant.model.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<RestaurantEntity,Long> {
    RestaurantEntity findByRestaurantId(String restaurantId);
    Page<RestaurantEntity> findAll(Pageable page);
}

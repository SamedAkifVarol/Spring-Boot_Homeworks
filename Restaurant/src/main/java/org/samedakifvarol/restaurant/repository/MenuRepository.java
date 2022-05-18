package org.samedakifvarol.restaurant.repository;

import org.samedakifvarol.restaurant.model.entity.MenuEntity;
import org.samedakifvarol.restaurant.model.entity.MenuItemEntity;
import org.samedakifvarol.restaurant.model.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    MenuEntity findTopByOrderByIdDesc();
    Page<MenuEntity> findAll(Pageable page);

    @Query(value = "SELECT * FROM menu WHERE restaurant_id =:restaurant_id",nativeQuery = true)
    List<MenuEntity> findByRestaurantId(@Param("restaurant_id") Long menu_id);
}

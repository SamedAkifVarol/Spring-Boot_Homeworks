package org.samedakifvarol.restaurant.repository;

import org.samedakifvarol.restaurant.model.entity.MenuEntity;
import org.samedakifvarol.restaurant.model.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    MenuEntity findTopByOrderByIdDesc();
    Page<MenuEntity> findAll(Pageable page);
}

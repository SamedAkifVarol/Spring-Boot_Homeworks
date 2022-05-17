package org.samedakifvarol.restaurant.repository;

import org.samedakifvarol.restaurant.model.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItemEntity,Long> {
    MenuItemEntity findTopByOrderByIdDesc();
    @Query(value = "SELECT * FROM menu_item WHERE menu_id =:menu_id",nativeQuery = true)
    List<MenuItemEntity> findByMenuId(@Param("menu_id") Long menu_id);
}

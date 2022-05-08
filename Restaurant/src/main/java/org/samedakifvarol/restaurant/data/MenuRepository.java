package org.samedakifvarol.restaurant.data;

import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<MenuEntity, Long> {
    MenuEntity findTopByOrderByIdDesc();
}

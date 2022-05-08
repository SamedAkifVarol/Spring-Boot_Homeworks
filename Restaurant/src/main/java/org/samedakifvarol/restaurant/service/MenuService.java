package org.samedakifvarol.restaurant.service;

import org.samedakifvarol.restaurant.model.MenuRequest;
import org.samedakifvarol.restaurant.shared.MenuDto;
import org.samedakifvarol.restaurant.shared.RestaurantDto;

public interface MenuService {
    MenuDto add(MenuDto menuDto);
    MenuDto update(MenuRequest menuRequest, Long id);
    void delete(Long id);
    MenuDto getMenu(Long id);
}

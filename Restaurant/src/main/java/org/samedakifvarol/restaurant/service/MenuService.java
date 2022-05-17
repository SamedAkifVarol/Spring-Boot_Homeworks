package org.samedakifvarol.restaurant.service;

import org.samedakifvarol.restaurant.controller.request.MenuRequest;
import org.samedakifvarol.restaurant.controller.response.MenuResponse;
import org.samedakifvarol.restaurant.model.dto.MenuDto;
import org.samedakifvarol.restaurant.model.dto.MenuItemDto;
import org.samedakifvarol.restaurant.model.entity.MenuItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuService {
    MenuResponse add(MenuRequest menuRequest);
    MenuResponse update(MenuRequest menuRequest, Long id);
    void delete(Long id);
    MenuDto getMenu(Long id);
    Page<MenuDto> getMenus(Pageable page);

}

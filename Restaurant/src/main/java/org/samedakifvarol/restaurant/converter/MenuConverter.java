package org.samedakifvarol.restaurant.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.samedakifvarol.restaurant.controller.request.MenuItemRequest;
import org.samedakifvarol.restaurant.controller.response.MenuResponse;
import org.samedakifvarol.restaurant.model.entity.MenuEntity;
import org.samedakifvarol.restaurant.model.dto.MenuDto;
import org.samedakifvarol.restaurant.model.entity.MenuItemEntity;

import java.util.ArrayList;
import java.util.List;

public final class MenuConverter {

    private MenuConverter() {

    }

    public static MenuResponse convert(MenuEntity menuEntity) {
        MenuResponse menu = new MenuResponse();
        List<MenuItemRequest> menuItemRequest = new ArrayList<>();

        for(int i = 0 ; i < menuEntity.getMenuItem().size();i++){
            menuItemRequest.get(i).setPrice(menuEntity.getMenuItem().get(i).getPrice());
            menuItemRequest.get(i).setPrice(menuEntity.getMenuItem().get(i).getPrice());
            menuItemRequest.get(i).setPrice(menuEntity.getMenuItem().get(i).getPrice());
        }

        menu.setName(menuEntity.getName());
        menu.setMenuItems(menuItemRequest);
        return menu;
    }
}

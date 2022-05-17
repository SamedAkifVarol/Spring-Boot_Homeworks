package org.samedakifvarol.restaurant.controller.response;

import lombok.Data;
import org.samedakifvarol.restaurant.controller.request.MenuItemRequest;

import java.util.List;

@Data
public class MenuResponse {
    private String name;
    private List<MenuItemRequest> menuItems;
}

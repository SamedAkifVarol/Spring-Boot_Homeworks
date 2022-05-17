package org.samedakifvarol.restaurant.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.samedakifvarol.restaurant.model.entity.MenuEntity;

import java.util.List;

@Data
@NoArgsConstructor
public class MenuDto {
    private Long id;
    private String name;
    private List<MenuItemDto> menuItem;

    public MenuDto(MenuEntity menuEntity) {
        this.id = menuEntity.getId();
        this.name = menuEntity.getName();
        this.menuItem = menuItem;
    }

}

package org.samedakifvarol.restaurant.converter;

import org.samedakifvarol.restaurant.data.MenuEntity;
import org.samedakifvarol.restaurant.shared.MenuDto;

public final class MenuConverter {

    private MenuConverter() {

    }

    public static MenuDto convert(MenuEntity menuEntity) {
        MenuDto menu = new MenuDto();

        menu.setCorbalar(menuEntity.getCorbalar());
        menu.setIcecekler(menuEntity.getIcecekler());
        menu.setTatlilar(menuEntity.getTatlilar());
        menu.setAnaYemekler(menuEntity.getAnaYemekler());

        return menu;
    }
}

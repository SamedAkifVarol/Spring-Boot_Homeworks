package org.samedakifvarol.restaurant.converter;

import org.samedakifvarol.restaurant.model.entity.RestaurantEntity;
import org.samedakifvarol.restaurant.model.dto.MenuDto;

public final class RestaurantConverter {
    private RestaurantConverter() {

    }

    public static RestaurantMenuDto convert(RestaurantEntity restaurantEntity) {

        RestaurantMenuDto restaurant = new RestaurantMenuDto();
        MenuDto menu = new MenuDto();

//        restaurant.setCity(restaurantEntity.getCity());
//        restaurant.setName(restaurantEntity.getName());
//        restaurant.setDistrict(restaurantEntity.getDistrict());
//        restaurant.setItem(restaurantEntity.getItem());
//        restaurant.setRestaurantId(restaurantEntity.getRestaurantId());
//        restaurant.setEncryptedPassword(restaurantEntity.getEncryptedPassword());
//        restaurant.setAnaYemekler(restaurantEntity.getMenu().getMa());
//        restaurant.setCorbalar(restaurantEntity.getMenu().getCorbalar());
//        restaurant.setIcecekler(restaurantEntity.getMenu().getIcecekler());
//        restaurant.setTatlilar(restaurantEntity.getMenu().getTatlilar());
        return restaurant;
    }

}

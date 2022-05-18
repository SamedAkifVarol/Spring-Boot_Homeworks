package org.samedakifvarol.restaurant.converter;

import org.samedakifvarol.restaurant.controller.response.UpdateRestaurant;
import org.samedakifvarol.restaurant.model.dto.RestaurantDto;
import org.samedakifvarol.restaurant.model.entity.RestaurantEntity;
import org.samedakifvarol.restaurant.model.dto.MenuDto;

public final class RestaurantConverter {
    private RestaurantConverter() {

    }

    public static RestaurantDto convert(RestaurantEntity restaurantEntity) {

        RestaurantDto restaurant = new RestaurantDto();
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

    public static UpdateRestaurant updatingConvert(RestaurantEntity restaurantEntity){
        UpdateRestaurant restaurant = new UpdateRestaurant();
        restaurant.setName(restaurantEntity.getName());
        restaurant.setCity(restaurantEntity.getCity());
        restaurant.setDistrict(restaurantEntity.getDistrict());
        restaurant.setItem(restaurantEntity.getItem());
        restaurant.setMenu_id(restaurantEntity.getMenu().stream().findAny().get().getId());
        return restaurant;
    }

}

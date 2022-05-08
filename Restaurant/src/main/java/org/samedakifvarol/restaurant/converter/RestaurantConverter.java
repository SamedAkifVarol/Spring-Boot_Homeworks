package org.samedakifvarol.restaurant.converter;

import org.samedakifvarol.restaurant.data.RestaurantEntity;
import org.samedakifvarol.restaurant.model.UpdateRestaurant;
import org.samedakifvarol.restaurant.shared.MenuDto;
import org.samedakifvarol.restaurant.shared.RestaurantMenuDto;

public final class RestaurantConverter {
    private RestaurantConverter() {

    }

    public static RestaurantMenuDto convert(RestaurantEntity restaurantEntity) {

        RestaurantMenuDto restaurant = new RestaurantMenuDto();
        MenuDto menu = new MenuDto();

        restaurant.setCity(restaurantEntity.getCity());
        restaurant.setName(restaurantEntity.getName());
        restaurant.setDistrict(restaurantEntity.getDistrict());
        restaurant.setItem(restaurantEntity.getItem());
        restaurant.setRestaurantId(restaurantEntity.getRestaurantId());
        restaurant.setEncryptedPassword(restaurantEntity.getEncryptedPassword());
        restaurant.setAnaYemekler(restaurantEntity.getMenu().getAnaYemekler());
        restaurant.setCorbalar(restaurantEntity.getMenu().getCorbalar());
        restaurant.setIcecekler(restaurantEntity.getMenu().getIcecekler());
        restaurant.setTatlilar(restaurantEntity.getMenu().getTatlilar());
        return restaurant;
    }

}

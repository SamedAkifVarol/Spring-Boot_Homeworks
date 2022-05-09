package org.samedakifvarol.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.samedakifvarol.restaurant.data.MenuEntity;
import org.samedakifvarol.restaurant.data.RestaurantEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRestaurantResponse {

    private String name;
    private String city;
    private String district;
    private String item;
    private String restaurantId;
    private MenuEntity menu;

    public GetRestaurantResponse(RestaurantEntity restaurantEntity) {
        this.name=restaurantEntity.getName();
        this.city=restaurantEntity.getCity();
        this.district=restaurantEntity.getDistrict();
        this.item=restaurantEntity.getItem();
        this.restaurantId=restaurantEntity.getRestaurantId();
        this.menu=restaurantEntity.getMenu();
    }
}

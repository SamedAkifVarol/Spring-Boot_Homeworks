package org.samedakifvarol.restaurant.controller.response;

import lombok.Data;

@Data
public class UpdateRestaurant {
    private String name;
    private String city;
    private String district;
    private String item;
    // Menu bilgileri -------------------
    private String soups;
    private String mainDishes;
    private String Deserts;
    private String Drinks;
}

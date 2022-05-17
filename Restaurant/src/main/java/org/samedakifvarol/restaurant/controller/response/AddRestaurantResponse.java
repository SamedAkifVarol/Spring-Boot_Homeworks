package org.samedakifvarol.restaurant.controller.response;

import lombok.Data;

import java.awt.*;

@Data
public class AddRestaurantResponse {
    private String name;
    private String city;
    private String district;
    private String item;
    private String restaurantId;
    private String corbalar;
    private String anaYemekler;
    private String tatlilar;
    private String icecekler;

}

package org.samedakifvarol.restaurant.model;

import lombok.Data;
import org.samedakifvarol.restaurant.data.MenuEntity;

@Data
public class UpdateRestaurant {
    private String name;
    private String city;
    private String district;
    private String item;
    // Menu bilgileri -------------------
    private String corbalar;
    private String anaYemekler;
    private String tatlilar;
    private String icecekler;
}

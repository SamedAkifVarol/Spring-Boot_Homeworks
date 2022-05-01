package org.samedakifvarol.restaurant.shared;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RestaurantDto implements Serializable {
    private static final long serialVersionUID = 8440825047434056167L;
    private String name,
            city,
            district,
            menu,
            item,
            restaurantId,
            password,
            encryptedPassword;

    public RestaurantDto(String name, String city, String district, String menu, String item, String restaurantId, String encryptedPassword) {
        this.name = name;
        this.city = city;
        this.district = district;
        this.menu = menu;
        this.item = item;
        this.restaurantId = restaurantId;
        this.encryptedPassword = encryptedPassword;
    }
}

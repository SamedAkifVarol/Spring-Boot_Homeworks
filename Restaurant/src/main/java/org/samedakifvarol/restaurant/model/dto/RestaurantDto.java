package org.samedakifvarol.restaurant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.samedakifvarol.restaurant.model.entity.MenuEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private String name;
    private String city;
    private String district;
    private String item;
    private String restaurantId;
    private String password;
    private String encryptedPassword;
    private MenuEntity menu;
}

package org.samedakifvarol.restaurant.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantMenuDto {
    private String name;
    private String city;
    private String district;
    private String item;
    private String restaurantId;
    private String password;
    private String encryptedPassword;
    private String corbalar;
    private String anaYemekler;
    private String tatlilar;
    private String icecekler;
}

package org.samedakifvarol.restaurant.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddRestaurantRequest {
    @NotNull(message = "Name can not be null")
    private String name;
    @NotNull(message = "city can not be null")
    private String city;
    @NotNull(message = "district can not be null")
    private String district;
    @NotNull(message = "item can not be null")
    private String item;
    @NotNull(message = "password can not be null")
    private String password;
    @NotNull(message = "menuId can not be null")
    private Long menu_id;

}

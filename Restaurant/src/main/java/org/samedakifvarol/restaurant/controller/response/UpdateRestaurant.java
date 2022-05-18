package org.samedakifvarol.restaurant.controller.response;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateRestaurant {
    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String district;

    @NotNull
    private String item;

    @NotNull
    private Long menu_id;

}

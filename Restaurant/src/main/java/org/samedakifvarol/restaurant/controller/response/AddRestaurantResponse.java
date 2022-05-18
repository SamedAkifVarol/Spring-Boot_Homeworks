package org.samedakifvarol.restaurant.controller.response;

import lombok.Data;
import org.samedakifvarol.restaurant.model.dto.MenuDto;

import java.util.List;


@Data
public class AddRestaurantResponse {
    private String name;
    private String city;
    private String district;
    private String item;
    private String restaurantId;
    private MenuDto menuDto;

}

package org.samedakifvarol.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.samedakifvarol.restaurant.data.MenuEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRestaurantResponse {

    private String name;
    private String city;
    private String district;
    private Long menu_id;
    private String item;
    private String restaurantId;
}

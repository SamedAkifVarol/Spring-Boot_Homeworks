package org.samedakifvarol.restaurant.controller.request;

import lombok.Data;
import org.samedakifvarol.restaurant.model.entity.MealType;

import java.math.BigDecimal;
@Data
public class MenuItemRequest {
    private Long id;
    private MealType mealType;
    private String value;
    private BigDecimal price;


}

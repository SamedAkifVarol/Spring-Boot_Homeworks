package org.samedakifvarol.restaurant.model.dto;

import lombok.Data;
import org.samedakifvarol.restaurant.model.entity.MealType;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class MenuItemDto {
    private Long id;
    private MealType mealType;
    private String Value;
    private BigDecimal price;
}

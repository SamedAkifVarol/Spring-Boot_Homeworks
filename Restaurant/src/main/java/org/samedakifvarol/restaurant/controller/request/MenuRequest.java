package org.samedakifvarol.restaurant.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class MenuRequest {
    @NotNull(message = "Name can not be null")
    private String name;
    @NotNull
    private List<MenuItemRequest> menuItems;
    /*
    Enum mealType (çorba,anayemek...)
    String Value (tarhana,mercimek gibi)
    Bigdecimal price (fiyat)
     */
}

package org.samedakifvarol.restaurant.model.entity;

public enum MealType {
    soups(1),
    mainDishes(2),
    deserts(3),
    drinks(4);

   private int i ;

    MealType(int i) {
        this.i = i;
    }
}

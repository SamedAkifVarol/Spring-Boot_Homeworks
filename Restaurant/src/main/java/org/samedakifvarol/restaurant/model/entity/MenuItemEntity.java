package org.samedakifvarol.restaurant.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MenuItem")
public class MenuItemEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private MealType mealType;
    private String Value;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;
}

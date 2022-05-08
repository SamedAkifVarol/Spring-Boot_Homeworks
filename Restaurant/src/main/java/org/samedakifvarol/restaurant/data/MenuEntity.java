package org.samedakifvarol.restaurant.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class MenuEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String corbalar;
    private String anaYemekler;
    private String tatlilar;
    private String icecekler;
//    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
//    private RestaurantEntity restaurant;
}

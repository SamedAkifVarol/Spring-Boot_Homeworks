package org.samedakifvarol.restaurant.model.entity;

import lombok.Data;
import org.samedakifvarol.restaurant.model.entity.MenuEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "restaurant")
public class RestaurantEntity implements Serializable {


    private static final long serialVersionUID = 6044619874105176186L;
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
	private String city;
	private String district;
	private String item;
	private String restaurantId;
	private String encryptedPassword;

	@OneToMany(mappedBy = "restaurant")
	private List<MenuEntity> menu = new ArrayList<>();

}

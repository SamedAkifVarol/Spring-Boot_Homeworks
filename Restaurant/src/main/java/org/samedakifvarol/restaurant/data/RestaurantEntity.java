package org.samedakifvarol.restaurant.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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
//	@OneToMany(mappedBy = "restaurant")
//	private List<MenuEntity> menu = new ArrayList<>();
	@ManyToOne(optional = false)
	@JoinColumn(name = "menu_id")
	private MenuEntity menu;
	private String item;
	private String restaurantId;
	private String encryptedPassword;

    public void setMenu(String corbalar, String anaYemekler, String icecekler, String tatlilar) {
    }
}

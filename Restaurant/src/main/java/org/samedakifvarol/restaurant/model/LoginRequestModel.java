package org.samedakifvarol.restaurant.model;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String restaurantId,password;

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}

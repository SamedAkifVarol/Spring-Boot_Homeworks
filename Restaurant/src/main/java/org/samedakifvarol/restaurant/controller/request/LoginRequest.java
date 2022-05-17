package org.samedakifvarol.restaurant.controller.request;

import lombok.Data;

@Data
public class LoginRequest {
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

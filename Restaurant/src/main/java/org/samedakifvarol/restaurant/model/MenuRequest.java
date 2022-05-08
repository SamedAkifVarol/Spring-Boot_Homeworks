package org.samedakifvarol.restaurant.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MenuRequest {
    @NotNull(message = "corbalar can not be null")
    private String corbalar;
    @NotNull(message = "anaYemekler can not be null")
    private String anaYemekler;
    @NotNull(message = "tatlilar can not be null")
    private String tatlilar;
    @NotNull(message = "icecekler can not be null")
    private String icecekler;
}

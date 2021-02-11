package br.com.zup.propostas.proposal;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Address {
    @NotBlank
    private String publicPlace;
    @NotBlank
    private String number;
    @NotBlank
    private String neighborhood;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String city;
    @NotBlank
    private String state;

    @Deprecated
    public Address() {
        
    }

    public Address(@NotBlank String publicPlace, @NotBlank String number, @NotBlank String neighborhood, @NotBlank String zipCode, @NotBlank String city, @NotBlank String state) {
        this.publicPlace = publicPlace;
        this.number = number;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }
}

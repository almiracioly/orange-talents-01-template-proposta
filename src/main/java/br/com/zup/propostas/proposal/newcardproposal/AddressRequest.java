package br.com.zup.propostas.proposal.newcardproposal;

import br.com.zup.propostas.proposal.Address;

import javax.validation.constraints.NotBlank;

public class AddressRequest {

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

    public AddressRequest(@NotBlank String publicPlace, @NotBlank String number, @NotBlank String neighborhood, @NotBlank String zipCode, @NotBlank String city, @NotBlank String state) {
        this.publicPlace = publicPlace;
        this.number = number;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public Address toModel() {
        return new Address(publicPlace, number, neighborhood, zipCode, city, state);
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}

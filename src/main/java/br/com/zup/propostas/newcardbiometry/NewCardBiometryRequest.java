package br.com.zup.propostas.newcardbiometry;

import br.com.zup.propostas.Biometry;
import br.com.zup.propostas.proposal.Card;

import javax.validation.constraints.NotBlank;

public class NewCardBiometryRequest {
    @NotBlank
    private String encodedBiometry;

    public String getEncodedBiometry() {
        return encodedBiometry;
    }

    public void setEncodedBiometry(String encodedBiometry) {
        this.encodedBiometry = encodedBiometry;
    }

    public Biometry toModel(Card card) {
        return new Biometry(encodedBiometry, card);
    }
}

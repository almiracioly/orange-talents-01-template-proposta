package br.com.zup.propostas.shared.thirdpartyapiclient.card.walletassociation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletAssociationRequest {

    private String email;

    @JsonProperty("carteira")
    private String wallet;

    public WalletAssociationRequest(String email, String wallet) {
        this.email = email;
        this.wallet = wallet;
    }

    public String getEmail() {
        return email;
    }

    public String getWallet() {
        return wallet;
    }
}

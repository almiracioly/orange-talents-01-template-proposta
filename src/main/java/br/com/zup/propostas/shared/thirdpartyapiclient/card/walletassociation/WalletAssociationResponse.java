package br.com.zup.propostas.shared.thirdpartyapiclient.card.walletassociation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletAssociationResponse {

    @JsonProperty("resultado")
    private String result;

    private String id;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

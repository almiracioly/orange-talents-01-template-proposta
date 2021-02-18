package br.com.zup.propostas.shared.thirdpartyapiclient.card;

public class CardLockRequest {
    private String sistemaResponsavel = "card-proposal-service";

    public CardLockRequest() {

    }

    public CardLockRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}

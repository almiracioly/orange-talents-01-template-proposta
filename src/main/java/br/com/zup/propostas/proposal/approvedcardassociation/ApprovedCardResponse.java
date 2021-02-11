package br.com.zup.propostas.proposal.approvedcardassociation;

public class ApprovedCardResponse {
    private String id;
    private String emitidoEm;
    private String titular;

    public ApprovedCardResponse(String id, String emitidoEm, String titular) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
    }

    public String getId() {
        return id;
    }

    public String getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }
}

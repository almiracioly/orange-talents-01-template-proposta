package br.com.zup.propostas.proposal.newcardproposal;

import br.com.zup.propostas.proposal.CardProposal;

public class CreditAnalysisRequest {
    private String documento;
    private String nome;
    private String idProposta;

    public CreditAnalysisRequest(CardProposal cardProposal) {
        documento = cardProposal.getPersonalDocument();
        nome = cardProposal.getName();
        idProposta = cardProposal.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}

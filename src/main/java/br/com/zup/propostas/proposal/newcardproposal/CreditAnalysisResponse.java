package br.com.zup.propostas.proposal.newcardproposal;

import br.com.zup.propostas.proposal.CardProposalStatus;

public class CreditAnalysisResponse {
    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private Long idProposta;

    public CreditAnalysisResponse(String documento, String nome, String resultadoSolicitacao, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public CardProposalStatus receivedStatusToModel(){
        return resultadoSolicitacao.equals("COM_RESTRICAO")
                ? CardProposalStatus.NAO_ELEGIVEL
                : CardProposalStatus.ELEGIVEL;
    }
}

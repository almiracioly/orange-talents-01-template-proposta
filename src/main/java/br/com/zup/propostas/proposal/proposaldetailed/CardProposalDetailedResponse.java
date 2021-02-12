package br.com.zup.propostas.proposal.proposaldetailed;

import br.com.zup.propostas.proposal.CardProposal;

public class CardProposalDetailedResponse {
    private Long id;
    private String name;
    private String status;

    public CardProposalDetailedResponse(CardProposal cardProposal) {
        id = cardProposal.getId();
        name = cardProposal.getName();
        status = cardProposal.getStatus().toString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}

package br.com.zup.propostas.shared.thirdpartyapiclient.card;

import br.com.zup.propostas.proposal.Card;
import br.com.zup.propostas.proposal.CardProposal;

import java.math.BigDecimal;

public class ApprovedCardResponse {
    private String id;
    private String emitidoEm;
    private String titular;
    private BigDecimal limite;

    public ApprovedCardResponse(String id, String emitidoEm, String titular, BigDecimal limite) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }

    public String getId() {
        return id;
    }

    public Card toModel(CardProposal cardProposal) {
        return new Card(limite, id,cardProposal);
    }
}

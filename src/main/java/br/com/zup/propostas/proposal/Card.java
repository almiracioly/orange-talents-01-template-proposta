package br.com.zup.propostas.proposal;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(nullable = false)
    private BigDecimal creditLimit;

    private LocalDateTime blockedAt;

    @OneToOne
    @JoinColumn(name = "proposal_id")
    private CardProposal cardProposal;

    @Deprecated
    public Card(){

    }

    public Card(BigDecimal creditLimit, CardProposal cardProposal) {
        this.creditLimit = creditLimit;
        this.cardProposal = cardProposal;
    }
}

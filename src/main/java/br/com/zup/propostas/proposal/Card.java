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

    private boolean blocked;

    @Column(nullable = false)
    private String cardNumber;

    @OneToOne
    @JoinColumn(name = "proposal_id")
    private CardProposal cardProposal;

    @Deprecated
    public Card(){

    }

    public Card(BigDecimal creditLimit, String cardNumber ,CardProposal cardProposal) {
        this.creditLimit = creditLimit;
        this.cardNumber = cardNumber;
        this.cardProposal = cardProposal;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public boolean getBlockedAt() {
        return blocked;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CardProposal getCardProposal() {
        return cardProposal;
    }

    public void lockCard() {
        blocked = true;
    }

    public boolean isBlocked() {
        return blocked == true;
    }
}

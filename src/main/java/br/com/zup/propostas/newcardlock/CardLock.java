package br.com.zup.propostas.newcardlock;

import br.com.zup.propostas.proposal.Card;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "card_locks")
public class CardLock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime lockedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Deprecated
    public CardLock() {

    }

    public CardLock(Card card) {
        this.card = card;
    }

}

package br.com.zup.propostas;

import br.com.zup.propostas.proposal.Card;

import javax.persistence.*;

@Entity
@Table(name = "card_biometrics")
public class Biometry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String value;

    @ManyToOne
    private Card card;

    @Deprecated
    public Biometry() {

    }

    public Biometry(String value, Card card) {
        this.value = value;
        this.card = card;
    }

    public Long getId() {
        return id;
    }
}

package br.com.zup.propostas.wallet;

import br.com.zup.propostas.proposal.Card;
import br.com.zup.propostas.wallet.newwalletassociation.AllowedWalletAssociation;

import javax.persistence.*;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AllowedWalletAssociation name;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    private String externalCardServiceId;

    @Deprecated
    public Wallet() {

    }

    public Wallet(String email, AllowedWalletAssociation walletName, Card card) {
        this.email = email;
        this.name = walletName;
        this.card = card;
    }

    public void setExternalCardServiceId(String externalCardServiceId) {
        this.externalCardServiceId = externalCardServiceId;
    }

    public Long getId() {
        return id;
    }
}

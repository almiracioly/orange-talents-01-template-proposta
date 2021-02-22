package br.com.zup.propostas.wallet.newwalletassociation;

import br.com.zup.propostas.proposal.Card;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.walletassociation.WalletAssociationRequest;
import br.com.zup.propostas.wallet.Wallet;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewWalletAssociationRequest {
    @NotBlank
    @Email
    private String email;

    @NotNull
    @Valid
    private AllowedWalletAssociation wallet;

    public WalletAssociationRequest toExternalApiRequest() {
        return new WalletAssociationRequest(email, wallet.toString());
    }

    public String getEmail() {
        return email;
    }

    public AllowedWalletAssociation getWallet() {
        return wallet;
    }

    public Wallet toModel(Card card) {
        return new Wallet(email, wallet, card);
    }
}

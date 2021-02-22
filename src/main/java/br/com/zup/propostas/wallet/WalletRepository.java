package br.com.zup.propostas.wallet;

import br.com.zup.propostas.proposal.Card;
import br.com.zup.propostas.wallet.newwalletassociation.AllowedWalletAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsByNameAndCard(AllowedWalletAssociation walletName, Card card);
}

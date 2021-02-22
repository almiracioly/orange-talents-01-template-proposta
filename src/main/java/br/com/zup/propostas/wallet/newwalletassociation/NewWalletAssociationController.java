package br.com.zup.propostas.wallet.newwalletassociation;

import br.com.zup.propostas.proposal.Card;
import br.com.zup.propostas.proposal.CardRepository;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.CardClient;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.walletassociation.WalletAssociationRequest;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.walletassociation.WalletAssociationResponse;
import br.com.zup.propostas.wallet.Wallet;
import br.com.zup.propostas.wallet.WalletRepository;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class NewWalletAssociationController {

    private CardRepository cardRepository;
    private WalletRepository walletRepository;
    private CardClient cardClient;

    public NewWalletAssociationController(CardRepository cardRepository, WalletRepository walletRepository, CardClient cardClient) {
        this.cardRepository = cardRepository;
        this.walletRepository = walletRepository;
        this.cardClient = cardClient;
    }

    @PostMapping("/cards/{cardNumber}/wallets-association")
    public ResponseEntity<?> exec(@PathVariable String cardNumber,
                                  @RequestBody @Valid NewWalletAssociationRequest request,
                                  UriComponentsBuilder uriBuilder) {

        Optional<Card> possibleCard = cardRepository.findByCardNumber(cardNumber);
        if (possibleCard.isEmpty()) return ResponseEntity.notFound().build();
        Card card = possibleCard.get();
        if (walletRepository.existsByNameAndCard(request.getWallet(), card))
            return ResponseEntity.unprocessableEntity().build();

        Wallet wallet = request.toModel(card);
        try {
            String externalCardServiceId = tryAssociateCardToWalletInExternalCardService(request, cardNumber);
            wallet.setExternalCardServiceId(externalCardServiceId);
            walletRepository.save(wallet);

            URI resourceUri = uriBuilder.path("/wallets-association/{walletId}").buildAndExpand(wallet.getId()).toUri();
            return ResponseEntity.created(resourceUri).build();
        } catch (FeignException feignException) {
            feignException.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    private String tryAssociateCardToWalletInExternalCardService(NewWalletAssociationRequest request, String cardNumber) {
        WalletAssociationRequest walletAssociationRequest = request.toExternalApiRequest();
        WalletAssociationResponse response = cardClient.associateToWallet(cardNumber, walletAssociationRequest);

        return response.getId();
    }
}

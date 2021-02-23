package br.com.zup.propostas.newcardlock;

import br.com.zup.propostas.proposal.Card;
import br.com.zup.propostas.proposal.CardRepository;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.CardClient;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.CardLockRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class NewCardLockController {

    private CardRepository cardRepository;
    @Autowired
    private CardClient cardClient;
    private EntityManager entityManager;

    public NewCardLockController(CardRepository cardRepository, EntityManager entityManager) {
        this.cardRepository = cardRepository;
        this.entityManager = entityManager;
    }

    @GetMapping("/cards/{cardNumber}/lock")
    @Transactional
    public ResponseEntity<?> exec(@PathVariable String cardNumber) {
        Optional<Card> possibleCard = cardRepository.findByCardNumber(cardNumber);
        if (!possibleCard.isPresent()) return ResponseEntity.notFound().build();

        Card card = possibleCard.get();
        try {
            cardClient.lockById(cardNumber, new CardLockRequest());
            registerCardLock(card);

            return ResponseEntity.ok().build();
        } catch (FeignException.UnprocessableEntity e) {
            if(!card.isBlocked()) registerCardLock(card);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new AlreadyLockedCardResponse());
        }
    }

    private void registerCardLock(Card card) {
        CardLock cardLock = new CardLock(card);
        entityManager.persist(cardLock);
        card.lockCard();
    }
}

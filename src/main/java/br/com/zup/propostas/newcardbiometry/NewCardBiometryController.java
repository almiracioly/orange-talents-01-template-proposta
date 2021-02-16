package br.com.zup.propostas.newcardbiometry;

import br.com.zup.propostas.Biometry;
import br.com.zup.propostas.proposal.Card;
import br.com.zup.propostas.proposal.CardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class NewCardBiometryController {

    private CardRepository cardRepository;
    private EntityManager entityManager;

    public NewCardBiometryController(CardRepository cardRepository, EntityManager entityManager) {
        this.cardRepository = cardRepository;
        this.entityManager = entityManager;
    }

    @PostMapping("cards/{cardId}/biometrics")
    @Transactional
    public ResponseEntity<?> exec(@PathVariable Long cardId, @RequestBody @Valid NewCardBiometryRequest request, UriComponentsBuilder uriBuilder) {
        Optional<Card> possibleCard = cardRepository.findById(cardId);
        if (!possibleCard.isPresent()) return ResponseEntity.notFound().build();

        Biometry biometry = request.toModel(possibleCard.get());
        entityManager.persist(biometry);

        URI uriToCreatedResource = uriBuilder.path("/biometrics/{biometryId}").buildAndExpand(cardId, biometry.getId()).toUri();

        return ResponseEntity.created(uriToCreatedResource).build();
    }
}

package br.com.zup.propostas.travelnotice.newtravelnotice;

import br.com.zup.propostas.proposal.Card;
import br.com.zup.propostas.proposal.CardRepository;
import br.com.zup.propostas.travelnotice.TravelNotice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
public class NewTravelNoticeController {

    private CardRepository cardRepository;
    private EntityManager entityManager;

    public NewTravelNoticeController(CardRepository cardRepository, EntityManager entityManager) {
        this.cardRepository = cardRepository;
        this.entityManager = entityManager;
    }

    @PostMapping("/travel-notices")
    @Transactional
    public ResponseEntity<?> exec(@PathParam("cardNumber") String cardNumber, @RequestBody @Valid NewTravelNoticeRequest request, HttpServletRequest httpRequest) {
        if (cardNumber == null) return ResponseEntity.badRequest().body(RequiredCardNumberWarn.build());

        Optional<Card> possibleCard = cardRepository.findByCardNumber(cardNumber);
        if (possibleCard.isEmpty()) return ResponseEntity.notFound().build();

        Card card = possibleCard.get();
        TravelNotice travelNotice = request.toModel(card, httpRequest);
        entityManager.persist(travelNotice);

        return ResponseEntity.ok().build();
    }

}

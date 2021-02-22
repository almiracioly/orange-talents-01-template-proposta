package br.com.zup.propostas.travelnotice.newtravelnotice;

import br.com.zup.propostas.proposal.Card;
import br.com.zup.propostas.proposal.CardRepository;
import br.com.zup.propostas.shared.genericdto.SimpleWarnMessage;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.CardClient;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.travelnoticenotification.NewTravelNoticeNotificationRequest;
import br.com.zup.propostas.travelnotice.TravelNotice;
import br.com.zup.propostas.travelnotice.TravelNoticeNotification;
import feign.FeignException;
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
    private CardClient cardClient;

    public NewTravelNoticeController(CardRepository cardRepository, EntityManager entityManager, CardClient cardClient) {
        this.cardRepository = cardRepository;
        this.entityManager = entityManager;
        this.cardClient = cardClient;
    }

    @PostMapping("/travel-notices")
    @Transactional
    public ResponseEntity<?> exec(@PathParam("cardNumber") String cardNumber, @RequestBody @Valid NewTravelNoticeRequest request, HttpServletRequest httpRequest) {
        if (cardNumber == null) return ResponseEntity.badRequest().body(RequiredCardNumberWarn.build());

        Optional<Card> possibleCard = cardRepository.findByCardNumber(cardNumber);
        if (possibleCard.isEmpty()) return ResponseEntity.notFound().build();

        Card card = possibleCard.get();
        TravelNotice travelNotice = request.toModel(card, httpRequest);

        try {
            TravelNoticeNotification travelNoticeNotification = notifyAccountApiAboutNewTravelNoticeBy(travelNotice);
            entityManager.persist(travelNotice);
            entityManager.persist(travelNoticeNotification);

            return ResponseEntity.ok().build();
        } catch (FeignException.UnprocessableEntity feignException) {
            return ResponseEntity.unprocessableEntity().body(
                    new SimpleWarnMessage("Verifique se destino  data de retorno já não foram informados em um registro anterior"));
        }

    }

    private TravelNoticeNotification notifyAccountApiAboutNewTravelNoticeBy(TravelNotice travelNotice) {
        NewTravelNoticeNotificationRequest notificationRequest = new NewTravelNoticeNotificationRequest(travelNotice);
        cardClient.notifyNewTravelNotice(travelNotice.getCard().getCardNumber(), notificationRequest);
        return new TravelNoticeNotification(travelNotice);
    }


}

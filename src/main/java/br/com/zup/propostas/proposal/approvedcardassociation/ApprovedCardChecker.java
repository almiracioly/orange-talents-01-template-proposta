package br.com.zup.propostas.proposal.approvedcardassociation;

import br.com.zup.propostas.proposal.*;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.ApprovedCardResponse;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.CardClient;
import feign.FeignException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApprovedCardChecker {

    private CardProposalRepository cardProposalRepository;
    private CardClient cardClient;
    private CardRepository cardRepository;

    public ApprovedCardChecker(CardProposalRepository cardProposalRepository, CardClient cardClient, CardRepository cardRepository) {
        this.cardProposalRepository = cardProposalRepository;
        this.cardClient = cardClient;
        this.cardRepository = cardRepository;
    }

    @Scheduled(fixedDelay = 10000)
    public void checkForApprovedCard() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        List<CardProposal> allByStatus = cardProposalRepository.findAllByStatus(CardProposalStatus.ELEGIVEL, pageable);

        if(allByStatus.isEmpty()) return;

        allByStatus.forEach(cardProposal -> {
            try {
                ApprovedCardResponse approvedCardResponse = cardClient.checkForCardByProposalId(cardProposal.getId().toString());
                Card card = approvedCardResponse.toModel(cardProposal);
                cardRepository.save(card);

                updateCardProposalWithApprovedCardInfo(cardProposal, approvedCardResponse);
            } catch (FeignException exception) {
                return;
            }
        });
    }

    private void updateCardProposalWithApprovedCardInfo(CardProposal cardProposal, ApprovedCardResponse approvedCardResponse) {
        cardProposal.setApprovedCardNumber(approvedCardResponse.getId());
        cardProposal.updateStatus(CardProposalStatus.CARTAO_RECEBIDO);
        cardProposalRepository.save(cardProposal);
    }
}

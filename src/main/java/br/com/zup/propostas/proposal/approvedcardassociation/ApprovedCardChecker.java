package br.com.zup.propostas.proposal.approvedcardassociation;

import br.com.zup.propostas.proposal.CardProposal;
import br.com.zup.propostas.proposal.CardProposalRepository;
import br.com.zup.propostas.proposal.CardProposalStatus;
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

    public ApprovedCardChecker(CardProposalRepository cardProposalRepository, CardClient cardClient) {
        this.cardProposalRepository = cardProposalRepository;
        this.cardClient = cardClient;
    }

    @Scheduled(fixedDelay = 10000)
    public void checkForApprovedCard() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        List<CardProposal> allByStatus = cardProposalRepository.findAllByStatus(CardProposalStatus.ELEGIVEL, pageable);

        if(allByStatus.isEmpty()) return;

        allByStatus.forEach(cardProposal -> {
            try {
                ApprovedCardResponse approvedCardResponse = cardClient.checkForCardByProposalId(cardProposal.getId().toString());
                cardProposal.setApprovedCardNumber(approvedCardResponse.getId());
                cardProposal.updateStatus(CardProposalStatus.CARTAO_RECEBIDO);
                cardProposalRepository.save(cardProposal);
            } catch (FeignException exception) {
                return;
            }
        });
    }
}

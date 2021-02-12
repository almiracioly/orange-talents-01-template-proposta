package br.com.zup.propostas.proposal.proposaldetailed;

import br.com.zup.propostas.proposal.CardProposal;
import br.com.zup.propostas.proposal.CardProposalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CardProposalDetailedController {

    private CardProposalRepository cardProposalRepository;

    public CardProposalDetailedController(CardProposalRepository cardProposalRepository) {
        this.cardProposalRepository = cardProposalRepository;
    }

    @GetMapping("card-proposals")
    public ResponseEntity<?> exec(@RequestParam Long proposalId) {
        Optional<CardProposal> possibleCardProposal = cardProposalRepository.findById(proposalId);
        if(!possibleCardProposal.isPresent()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new CardProposalDetailedResponse(possibleCardProposal.get()));
    }
}

package br.com.zup.propostas.proposal.newcardproposal;

import br.com.zup.propostas.proposal.*;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class NewCardProposalController {

    private CardProposalRepository cardProposalRepository;
    private CreditAnalysisClient creditAnalysisClient;

    public NewCardProposalController(CardProposalRepository cardProposalRepository, CreditAnalysisClient creditAnalysisClient) {
        this.cardProposalRepository = cardProposalRepository;
        this.creditAnalysisClient = creditAnalysisClient;
    }

    @PostMapping("card-proposals")
    public ResponseEntity<?> exec(@RequestBody @Valid NewCardProposalRequest request, UriComponentsBuilder uriBuilder){
        if(cardProposalRepository.existsByPersonalDocument(request.getPersonalDocument()))
            return ResponseEntity.unprocessableEntity().build();


        CardProposal cardProposal = request.toModel();
        cardProposalRepository.save(cardProposal);

        cardProposal.updateStatus(getAnalysisStatusResponse(cardProposal));
        cardProposalRepository.save(cardProposal);

        URI resourceUrl = uriBuilder.path("card-proposals/{id}").buildAndExpand(cardProposal.getId()).toUri();
        return ResponseEntity.created(resourceUrl).build();
    }

    private CardProposalStatus getAnalysisStatusResponse(CardProposal cardProposal){
        try {
            CreditAnalysisResponse analysisResponse = creditAnalysisClient
                    .analyze(new CreditAnalysisRequest(cardProposal));

            return analysisResponse.receivedStatusToModel();
        } catch (FeignException.UnprocessableEntity e) {
            return CardProposalStatus.NAO_ELEGIVEL;
        }

    }
}

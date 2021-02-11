package br.com.zup.propostas.proposal.newcardproposal;

import br.com.zup.propostas.proposal.CardProposal;
import br.com.zup.propostas.proposal.CardProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class NewCardProposalController {

    @Autowired
    private CardProposalRepository cardProposalRepository;

    @PostMapping("card-proposals")
    @Transactional
    public ResponseEntity<?> exec(@RequestBody @Valid NewCardProposalRequest request, UriComponentsBuilder uriBuilder){
        if(cardProposalRepository.existsByPersonalDocument(request.getPersonalDocument()))
            return ResponseEntity.unprocessableEntity().build();

        CardProposal cardProposal = request.toModel();
        cardProposalRepository.save(cardProposal);

        URI resourceUrl = uriBuilder.path("card-proposals/{id}").buildAndExpand(cardProposal.getId()).toUri();

        return ResponseEntity.created(resourceUrl).build();
    }
}

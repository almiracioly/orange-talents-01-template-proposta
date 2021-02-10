package br.com.zup.propostas.proposal.newcardproposal;

import br.com.zup.propostas.proposal.CardProposal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class NewCardProposalController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping("card-proposals")
    @Transactional
    public ResponseEntity<?> exec(@RequestBody @Valid NewCardProposalRequest request, UriComponentsBuilder uriBuilder){
        CardProposal cardProposal = request.toModel();
        entityManager.persist(cardProposal);

        URI resourceUrl = uriBuilder.path("card-proposals/{id}").buildAndExpand(cardProposal.getId()).toUri();

        return ResponseEntity.created(resourceUrl).build();
    }
}

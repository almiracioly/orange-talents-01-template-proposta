package br.com.zup.propostas.proposal.approvedcardassociation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cardClient", url = "http://localhost:8888/api")
public interface CardClient {

    @GetMapping("/cartoes")
    public ApprovedCardResponse checkForCardByProposalId(@RequestParam("idProposta") String proposalId);
}

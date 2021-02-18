package br.com.zup.propostas.shared.thirdpartyapiclient.card;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cardClient", url = "http://localhost:8888/api")
public interface CardClient {

    @GetMapping("/cartoes")
    public ApprovedCardResponse checkForCardByProposalId(@RequestParam("idProposta") String proposalId);

    @PostMapping("/cartoes/{id}/bloqueios")
    CardLockResponse lockById(@PathVariable("id") String id, @RequestBody CardLockRequest request);
}

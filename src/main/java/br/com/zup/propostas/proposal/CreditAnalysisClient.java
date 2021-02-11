package br.com.zup.propostas.proposal;

import br.com.zup.propostas.proposal.newcardproposal.CreditAnalysisRequest;
import br.com.zup.propostas.proposal.newcardproposal.CreditAnalysisResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "creditAnalysis", url = "http://localhost:9999/api")
public interface CreditAnalysisClient {

    @PostMapping("/solicitacao")
    CreditAnalysisResponse analyze(@RequestBody CreditAnalysisRequest request);

}

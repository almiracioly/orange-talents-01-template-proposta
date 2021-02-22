package br.com.zup.propostas.shared.thirdpartyapiclient.card;

import br.com.zup.propostas.shared.thirdpartyapiclient.card.travelnoticenotification.NewTravelNoticeNotificationRequest;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.travelnoticenotification.NewTravelNoticeNotificationResponse;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.walletassociation.WalletAssociationRequest;
import br.com.zup.propostas.shared.thirdpartyapiclient.card.walletassociation.WalletAssociationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(value = "cardClient", url = "http://localhost:8888/api")
public interface CardClient {

    @GetMapping("/cartoes")
    public ApprovedCardResponse checkForCardByProposalId(@RequestParam("idProposta") String proposalId);

    @PostMapping("/cartoes/{id}/bloqueios")
    CardLockResponse lockById(@PathVariable("id") String id, @RequestBody CardLockRequest request);

    @PostMapping("/cartoes/{id}/avisos")
    NewTravelNoticeNotificationResponse notifyNewTravelNotice(@PathVariable("id") String cardNumber,
                                                              @RequestBody @Valid NewTravelNoticeNotificationRequest notificationRequest);

    @PostMapping("/cartoes/{id}/carteiras")
    WalletAssociationResponse associateToWallet(@PathVariable("id") String cardNumber,
                                                @RequestBody @Valid WalletAssociationRequest walletAssociationRequest);
}

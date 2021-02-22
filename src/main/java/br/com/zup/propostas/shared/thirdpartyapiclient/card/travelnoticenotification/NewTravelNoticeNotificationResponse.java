package br.com.zup.propostas.shared.thirdpartyapiclient.card.travelnoticenotification;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewTravelNoticeNotificationResponse {
    @JsonProperty("resultado")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

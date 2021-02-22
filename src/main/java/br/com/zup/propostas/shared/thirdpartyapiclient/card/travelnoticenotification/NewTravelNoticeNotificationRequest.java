package br.com.zup.propostas.shared.thirdpartyapiclient.card.travelnoticenotification;

import br.com.zup.propostas.travelnotice.TravelNotice;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class NewTravelNoticeNotificationRequest {
    @JsonProperty("destino")
    private String travelDestiny;

    @JsonProperty("validoAte")
    private LocalDate returnDate;

    public NewTravelNoticeNotificationRequest(TravelNotice travelNotice) {
        travelDestiny = travelNotice.getTravelDestiny();
        returnDate = travelNotice.getReturnDate();
    }

    public String getTravelDestiny() {
        return travelDestiny;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}

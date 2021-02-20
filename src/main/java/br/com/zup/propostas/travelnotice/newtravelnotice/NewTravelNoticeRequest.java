package br.com.zup.propostas.travelnotice.newtravelnotice;

import br.com.zup.propostas.proposal.Card;
import br.com.zup.propostas.travelnotice.TravelNotice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.google.common.base.MoreObjects.firstNonNull;

public class NewTravelNoticeRequest {
    @NotBlank
    private String travelDestiny;

    @NotNull
    @Future
    private LocalDate returnDate;

    public NewTravelNoticeRequest(@NotBlank String travelDestiny, @NotNull @Future LocalDate returnDate) {
        this.travelDestiny = travelDestiny;
        this.returnDate = returnDate;
    }

    public String getTravelDestiny() {
        return travelDestiny;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public TravelNotice toModel(Card card, HttpServletRequest httpRequest) {
        TravelNotice travelNotice = new TravelNotice(card, travelDestiny, returnDate);

        setRequestingUserAgentIfItsExists(httpRequest, travelNotice);
        tryToSetRequestingIpAddressToTravelNotice(httpRequest, travelNotice);
        
        return travelNotice;
    }

    private void tryToSetRequestingIpAddressToTravelNotice(HttpServletRequest httpRequest, TravelNotice travelNotice) {
        String remoteIpAddress = getRemoteIpAddress(httpRequest);
        if(remoteIpAddress != null) travelNotice.setRequestingIp(remoteIpAddress);
    }

    private void setRequestingUserAgentIfItsExists(HttpServletRequest httpRequest, TravelNotice travelNotice) {
        String userAgent = httpRequest.getHeader("User-Agent");
        if (userAgent != null) travelNotice.setRequestingUserAgent(userAgent);
    }

    private String getRemoteIpAddress(HttpServletRequest httpRequest) {
        String xRealIp = httpRequest.getHeader("X-Real-IP");
        String xForwardedFor = httpRequest.getHeader("X-Forwarded-For");
        String remoteAddr = httpRequest.getRemoteAddr();

        if (xRealIp != null)
            return xRealIp;

        return firstNonNull(xForwardedFor, remoteAddr);
    }
}

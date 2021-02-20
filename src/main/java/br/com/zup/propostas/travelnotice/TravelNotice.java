package br.com.zup.propostas.travelnotice;

import br.com.zup.propostas.proposal.Card;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "travel_notices")
public class TravelNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Column(nullable = false)
    private String travelDestiny;

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private String requestingIp;

    private String requestUserAgent;

    public TravelNotice(Card card, String travelDestiny, LocalDate returnDate) {
        this.card = card;
        this.travelDestiny = travelDestiny;
        this.returnDate = returnDate;
    }

    public void setRequestingUserAgent(String userAgent) {
        requestUserAgent = userAgent;
    }

    public void setRequestingIp(String requestingIp) {
        this.requestingIp = requestingIp;
    }
}

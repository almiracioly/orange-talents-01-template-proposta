package br.com.zup.propostas.travelnotice;

import br.com.zup.propostas.proposal.Card;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "travel_notice_notifications")
public class TravelNoticeNotification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @OneToOne
    @JoinColumn(name = "travel_notice_id", nullable = false)
    private TravelNotice travelNotice;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


    public TravelNoticeNotification(TravelNotice travelNotice) {
        this.travelNotice = travelNotice;
    }
}

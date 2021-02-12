package br.com.zup.propostas.proposal;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "proposals")
public class CardProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String personalDocument;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Embedded
    private Address address;
    @Column(nullable = false)
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private CardProposalStatus status;
    private String approvedCardNumber;

    @Deprecated
    public CardProposal() {

    }

    public CardProposal(String personalDocument, String email, String name, Address address, BigDecimal salary) {
        this.personalDocument = personalDocument;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getPersonalDocument() {
        return personalDocument;
    }

    public String getName() {
        return name;
    }

    public CardProposalStatus getStatus() {
        return status;
    }

    public void updateStatus(CardProposalStatus cardProposalStatus) {
        status = cardProposalStatus;
    }

    public void setApprovedCardNumber(String cardNumber) {
        approvedCardNumber = cardNumber;
    }
}

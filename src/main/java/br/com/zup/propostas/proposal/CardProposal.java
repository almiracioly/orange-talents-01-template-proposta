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
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private BigDecimal salary;

    @Deprecated
    public CardProposal() {

    }

    public CardProposal(String personalDocument, String email, String name, String address, BigDecimal salary) {
        this.personalDocument = personalDocument;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }
}

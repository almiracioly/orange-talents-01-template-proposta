package br.com.zup.propostas.proposal.newcardproposal;

import br.com.zup.propostas.proposal.CardProposal;
import br.com.zup.propostas.shared.customannotation.cpforcnpj.CpfOrCnpj;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NewCardProposalRequest {

    @NotBlank
    @CpfOrCnpj
    private String personalDocument;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotNull
    @Positive
    private BigDecimal salary;

    public NewCardProposalRequest(@NotBlank String personalDocument, @NotBlank @Email String email, @NotBlank String name, @NotBlank String address, @NotNull @Positive BigDecimal salary) {
        this.personalDocument = personalDocument;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public CardProposal toModel() {
        return new CardProposal(personalDocument, email, name, address, salary);
    }
}

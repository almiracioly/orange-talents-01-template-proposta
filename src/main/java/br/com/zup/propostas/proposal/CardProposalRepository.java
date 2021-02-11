package br.com.zup.propostas.proposal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardProposalRepository extends JpaRepository<CardProposal, Long> {

    boolean existsByPersonalDocument(String personalDocument);

}

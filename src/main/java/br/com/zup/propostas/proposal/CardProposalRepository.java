package br.com.zup.propostas.proposal;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardProposalRepository extends JpaRepository<CardProposal, Long> {

    boolean existsByPersonalDocument(String personalDocument);

    List<CardProposal> findAllByStatus(CardProposalStatus status, Pageable pageable);
}

package org.rafaelts.faithwords_library_backend.domain.repository;

import org.rafaelts.faithwords_library_backend.domain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    // Por exemplo, listar empréstimos ativos
    // List<Loan> findByReturnedFalse();
}


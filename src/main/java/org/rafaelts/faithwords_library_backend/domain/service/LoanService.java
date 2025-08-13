package org.rafaelts.faithwords_library_backend.domain.service;

import java.util.List;
import java.util.Optional;

import org.rafaelts.faithwords_library_backend.domain.model.Loan;
import org.rafaelts.faithwords_library_backend.domain.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Optional<Loan> findById(Long id) {
        return loanRepository.findById(id);
    }

    // Pode implementar métodos como "findActiveLoans", "markAsReturned" etc.
}

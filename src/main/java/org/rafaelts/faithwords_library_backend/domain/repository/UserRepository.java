package org.rafaelts.faithwords_library_backend.domain.repository;

import org.rafaelts.faithwords_library_backend.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

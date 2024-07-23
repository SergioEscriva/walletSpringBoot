package dev.sergioescriva.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sergioescriva.wallet.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

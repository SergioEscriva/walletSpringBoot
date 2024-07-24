package dev.sergioescriva.wallet.repositories;

import org.springframework.data.repository.CrudRepository;

import dev.sergioescriva.wallet.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

}

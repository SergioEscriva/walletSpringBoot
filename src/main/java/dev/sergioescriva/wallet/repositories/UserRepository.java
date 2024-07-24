package dev.sergioescriva.wallet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.sergioescriva.wallet.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

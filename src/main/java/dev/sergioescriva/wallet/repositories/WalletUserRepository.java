package dev.sergioescriva.wallet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletUserRepository extends CrudRepository<WalletUserRepository, Long> {

}

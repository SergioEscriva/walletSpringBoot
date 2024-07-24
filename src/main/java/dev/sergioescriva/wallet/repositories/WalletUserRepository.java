package dev.sergioescriva.wallet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.sergioescriva.wallet.models.WalletUser;

@Repository
public interface WalletUserRepository extends CrudRepository<WalletUser, Long> {

}

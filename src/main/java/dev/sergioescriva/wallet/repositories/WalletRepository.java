package dev.sergioescriva.wallet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.sergioescriva.wallet.models.Wallet;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

}

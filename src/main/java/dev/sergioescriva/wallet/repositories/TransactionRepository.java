package dev.sergioescriva.wallet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.sergioescriva.wallet.models.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}

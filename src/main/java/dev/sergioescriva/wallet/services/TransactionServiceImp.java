package dev.sergioescriva.wallet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sergioescriva.wallet.models.Transaction;
import dev.sergioescriva.wallet.repositories.TransactionRepository;

@Service
public class TransactionServiceImp implements TransactionService {
    @Autowired
    TransactionRepository repository;

    @Override
    public List<Transaction> getAllTransactionByWalletId(Long walletId) {
        List<Transaction> transactionList = new ArrayList<>();
        Iterable<Transaction> transactions = repository.findAll();
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(walletId)) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {

        Optional<Transaction> transaction = repository.findById(transactionId);
        if (transaction.isPresent()) {
            return transaction.get();
        }
        return null;
    }

    @Override
    public Double getBalanceById(Long walletId) {
        List<Transaction> transactions = getAllTransactionByWalletId(walletId);
        Double balance = 0d;

        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }

        return balance;

    }

    @Override
    public void getBalanceMinById(Long walletId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBalanceMinById'");
    }

    @Override
    public void addTransaction(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        repository.save(transaction);

    }

    @Override
    public void delTransactionById(Long delId) {
        repository.deleteById(delId);
    }

}

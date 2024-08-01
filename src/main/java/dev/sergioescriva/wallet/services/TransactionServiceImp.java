package dev.sergioescriva.wallet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sergioescriva.wallet.models.Transaction;
import dev.sergioescriva.wallet.models.User;
import dev.sergioescriva.wallet.repositories.TransactionRepository;
import dev.sergioescriva.wallet.repositories.UserRepository;

@Service
public class TransactionServiceImp implements TransactionService {
    @Autowired
    TransactionRepository repository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Transaction> getAllTransactionByWalletId(Long walletId) {
        List<Transaction> transactionList = new ArrayList<>();
        Iterable<Transaction> transactions = repository.findAll();
        for (Transaction transaction : transactions) {
            if (transaction.getWalletId().equals(walletId)) {
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
    public User getBalanceMinById(Long walletId) {
        User prueba = new User();
        prueba.setUsername("Sergio1");
        return prueba;
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
    /*
     * @Override
     * public User getParticipantsNames(Long walletId) {
     * 
     * public User participantsIdListToNameList(Long walletId) {
     * String participantsName = "";
     * Iterable<Transaction> transactions = repository.findAll();
     * 
     * String array = participantsIdList.split(",");
     * System.out.println("QQQQQQQQQQQQQQQQQQQQ : " + array);
     * for (Transaction transaction : transactions) {
     * if (transaction.getWalletId().equals(walletId)) {
     * participantName = userRepository.findById(transaction.getParticipants());
     * }
     * console.log(participantNameNow + " jjjj " + participantId);
     * participantsName += participantNameNow.username;
     * }
     * return participantsName;
     * }
     */
}

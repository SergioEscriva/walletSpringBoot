package dev.sergioescriva.wallet.services;

import org.springframework.stereotype.Service;

import dev.sergioescriva.wallet.models.Transaction;

@Service
public class TransactionServiceImp implements TransactionService {

    @Override
    public void getAllTransactionByWalletId(Long walletId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTransactionByWalletId'");
    }

    @Override
    public void getTransactionById(Long transactionId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTransactionById'");
    }

    @Override
    public void getBalanceById(Long walletId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBalanceById'");
    }

    @Override
    public void getBalanceMinById(Long walletId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBalanceMinById'");
    }

    @Override
    public void addTransaction(Transaction transaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransaction'");
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTransaction'");
    }

    @Override
    public void delTransactionById(Long delId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delTransactionById'");
    }

}

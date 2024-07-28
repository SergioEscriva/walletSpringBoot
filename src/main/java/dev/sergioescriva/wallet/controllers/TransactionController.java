package dev.sergioescriva.wallet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sergioescriva.wallet.models.Transaction;
import dev.sergioescriva.wallet.services.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionService service;

    @GetMapping("/wallet/{walletId}")
    public List<Transaction> getAllTransactionByWalletId(@PathVariable Long walletId) {
        return service.getAllTransactionByWalletId(walletId);
        // transaction = Transaction().transactions(wallet_id)
        // return transaction
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactionById(@PathVariable Long transactionId) {
        return service.getTransactionById(transactionId);
        // transaction = Transaction().transaction(transaction_id)
        // return transaction

    }

    @GetMapping("/balance/{walletId}")
    public Double getBalanceById(@PathVariable Long walletId) {
        return service.getBalanceById(walletId);
        // transaction = Transaction().amountTotal(wallet_id)
        // return transaction

    }

    @GetMapping("/balance_min/{walletId}")
    public void getBalanceMinById(@PathVariable Long walletId) {
        // transaction = Transaction().balance(wallet_id)["member_min"]
        // return transaction
    }

    @PostMapping("/transaction/")
    public void addTransaction(@RequestBody Transaction transaction) {
        service.addTransaction(transaction);
        // public void addTransaction(data_transaction: transactionAddDic){
        // data_transaction_dic = data_transaction.dict()
        // transaction = Transaction().add(data_transaction_dic)
        // return transaction
    }

    @PutMapping
    public void updateTransaction(@RequestBody Transaction transaction) {
        service.updateTransaction(transaction);
        // public void updateTransaction(data_transaction: transactionAddDic){
        // data_transaction_dic = data_transaction.dict()
        // transaction = Transaction().update(data_transaction_dic)
        // return transaction
    }

    @DeleteMapping("/{del_id}")
    public void delTransactionById(@PathVariable Long delId) {
        service.delTransactionById(delId);
        // transaction = Transaction().delete(del_id)
        // return transaction
    }
}

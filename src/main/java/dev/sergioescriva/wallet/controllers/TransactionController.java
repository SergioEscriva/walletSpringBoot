package dev.sergioescriva.wallet.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sergioescriva.wallet.models.Transaction;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @GetMapping("/{walletId}")
    public void getAllTransactionByWalletId(@PathVariable Long walletId) {
        // transaction = Transaction().transactions(wallet_id)
        // return transaction
    }

    @GetMapping("/{transactionId}")
    public void getTransactionById(@PathVariable Long transactionId) {
        // transaction = Transaction().transaction(transaction_id)
        // return transaction

    }

    @GetMapping("/balance/{walletId}")
    public void getBalanceById(@PathVariable Long walletId) {
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
        // public void addTransaction(data_transaction: transactionAddDic){
        // data_transaction_dic = data_transaction.dict()
        // transaction = Transaction().add(data_transaction_dic)
        // return transaction
    }

    @PutMapping
    public void updateTransaction(@RequestBody Transaction transaction) {
        // public void updateTransaction(data_transaction: transactionAddDic){
        // data_transaction_dic = data_transaction.dict()
        // transaction = Transaction().update(data_transaction_dic)
        // return transaction
    }

    @DeleteMapping("/{del_id}")
    public void delTransactionById(@PathVariable Long delId) {
        // transaction = Transaction().delete(del_id)
        // return transaction
    }
}

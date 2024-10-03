package dev.sergioescriva.wallet.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sergioescriva.wallet.models.Transaction;
import dev.sergioescriva.wallet.models.User;
import dev.sergioescriva.wallet.services.TransactionService;
import dev.sergioescriva.wallet.services.WalletService;
import dev.sergioescriva.wallet.utilities.Settle;

@RestController
@RequestMapping("/api/settle")
public class SettleController {

    Settle settle = new Settle();

    @Autowired
    TransactionService TransactionService;

        @Autowired
    WalletService WalletService;

    @GetMapping("/{walletId}")
    public ArrayList<ArrayList> getDivisionByWalletId(@PathVariable Long walletId) {
        List<Transaction> transactions = TransactionService.getAllTransactionByWalletId(walletId);
        List<User> users = WalletService.getMembersById(walletId);
        
        //ArrayList<ArrayList> settle = settle.resolucionDeudaWallet();
         return settle.sumaTransactions(transactions, users);
        
    }
}

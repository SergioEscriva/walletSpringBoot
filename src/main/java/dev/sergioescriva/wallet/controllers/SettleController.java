package dev.sergioescriva.wallet.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/settle")
public class SettleController {

    @GetMapping("/{wallet_id}")
    public Double getDivisionByWalletId(@PathVariable Long walletId) {
        return (double) 256;
        // settle = Settle().divisionWallet(wallet_id)
        // return settle
    }
}

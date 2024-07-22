package dev.sergioescriva.wallet.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @GetMapping("/{proprietary}")
    public String getWalletByPropietary(@RequestParam Long proprietaryId) {
        // wallets = Wallet().readWallets(proprietary)
        // Wallet wallets =
        return new String();
    }

    @GetMapping("/id/{walletName}")
    public String getWalletByName(@RequestParam String walletName) {
        // wallet = Wallet().walletNameToId(wallet_name)
        return new String();
    }

    @PostMapping("/{walletName}/{proprietary}")

    public void addWallet(@RequestParam String walletName, @RequestParam Long proprietary) {
        // wallet = Wallet().addWallet(wallet_name,proprietary)

    }

    @PutMapping("/{nameOld}/{nameNew}")
    public void updateWalletName(@RequestParam String nameOld, @RequestParam String nameNew) {
        // wallet = Wallet().updateWallet(name_old, name_new)
        // return wallet
    }

    @DeleteMapping("/{wallet_id}")
    public void delWalletById(@RequestParam Long walletId) {
        // wallet = Wallet().deleteWallet(wallet_id)
        // return wallet
    }

    // description

    @GetMapping("/description/{walletId}")

    public void getDescriptionById(@RequestParam Long walletId) {
        // wallets = Wallet().readDescription(wallet_id)
        // return wallets
    }

    @PutMapping("/description/{walletId}/{description}")
    public void updateDescription(@RequestParam Long walletId, @RequestParam String description) {
        // wallet = Wallet().updateDescription(wallet_id,description)
        // return wallet
    }

    @DeleteMapping("/description/{walletId}")
    public void delDescriptionById(@RequestParam Long walletId) {
        // wallet = Wallet().deleteDescription(wallet_id)
        // return wallet
    }

    @PutMapping("/share/{walletId}/{share}")
    public void updateShareById(@RequestParam Long walletId, Boolean share) {
        // wallet = Wallet().share(wallet_id,share)
        // return wallet
    }

    @GetMapping("/members/{wallet_id}")
    public void getMembersById(@RequestParam Long walletId) {
        // wallets = Wallet().membersWallet(wallet_id)#[0]["user_id"]
        // return wallets
    }

    @PostMapping("/{walletId}/member/{memberName}/{pin}")
    public void addMemberToWalletId(@RequestParam Long walletId, @RequestParam String membeName,
            @RequestParam Integer pin) {
        // wallet = Wallet().addMember(wallet_id, member_name, pin)
        // return wallet
    }

    @DeleteMapping("/{walletId}/member/{delId}")
    public void delMemberToWalletId(@RequestParam Long walletId, @RequestParam Long delId) {
        // wallet = Wallet().deleteMember(wallet_id, del_id)
        // return wallet
    }

    // proprietary

    @PutMapping("/{walletId}/proprietary/{proprietary}")
    public void updateProprietaryByWalletId(@RequestParam Long walletId, @RequestParam Long proprietary) {
        // wallet = Wallet().updateProprietary(wallet_id,proprietary)
        // return wallet
    }

}

package dev.sergioescriva.wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sergioescriva.wallet.services.WalletService;

@RestController
@CrossOrigin
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    WalletService service;

    @CrossOrigin
    @GetMapping("/{proprietaryId}")
    public String getWalletByPropietary(@PathVariable Long proprietaryId) {
        service.getWalletByPropietary(proprietaryId);
        // wallets = Wallet().readWallets(proprietary)
        // Wallet wallets =.
        System.out.println("propietariooooooos");

        return new String();
    }

    @GetMapping("/id/{walletName}")
    public String getWalletByName(@PathVariable String walletName) {
        service.getWalletByName(walletName);
        // wallet = Wallet().walletNameToId(wallet_name)
        return new String();
    }

    @PostMapping("/{walletName}/{proprietaryId}")
    public void addWallet(@PathVariable String walletName, @PathVariable Long proprietaryId) {
        service.addWallet(walletName, proprietaryId);

        // wallet = Wallet().addWallet(wallet_name,proprietary)

    }

    @PutMapping("/{id}/{nameNew}")
    public void updateWalletName(@PathVariable Long id, @PathVariable String nameNew) {
        service.updateWalletName(id, nameNew);
        // wallet = Wallet().updateWallet(name_old, name_new)
        // return wallet
    }

    @DeleteMapping("/{walletId}")
    public void delWalletById(@PathVariable Long walletId) {
        service.delWalletById(walletId);
        // wallet = Wallet().deleteWallet(wallet_id)
        // return wallet
    }

    // description

    @GetMapping("/description/{walletId}")
    public void getDescriptionById(@PathVariable Long walletId) {
        service.getDescriptionById(walletId);
        // wallets = Wallet().readDescription(wallet_id)
        // return wallets
    }

    @PutMapping("/description/{walletId}/{description}")
    public void updateDescription(@PathVariable Long walletId, @PathVariable String description) {
        service.updateDescription(walletId, description);
        // wallet = Wallet().updateDescription(wallet_id,description)
        // return wallet
    }

    @DeleteMapping("/description/{walletId}")
    public void delDescriptionById(@PathVariable Long walletId) {
        service.delDescriptionById(walletId);
        // wallet = Wallet().deleteDescription(wallet_id)
        // return wallet
    }

    @PutMapping("/share/{walletId}/{share}")
    public void updateShareById(@PathVariable Long walletId, Boolean share) {
        service.updateShareById(walletId, share);
        // wallet = Wallet().share(wallet_id,share)
        // return wallet
    }

    @GetMapping("/members/{walletId}")
    public void getMembersById(@PathVariable Long walletId) {
        service.getMembersById(walletId);
        // wallets = Wallet().membersWallet(wallet_id)#[0]["user_id"]
        // return wallets
    }

    @PostMapping("/{walletId}/member/{memberId}/{pin}")
    public void addMemberToWalletId(@PathVariable Long walletId, @PathVariable Long membeId,
            @PathVariable Integer pin) {
        service.addMemberToWalletId(walletId, membeId, pin);
        // wallet = Wallet().addMember(wallet_id, member_name, pin)
        // return wallet
    }

    @DeleteMapping("/{walletId}/member/{delId}")
    public void delMemberToWalletId(@PathVariable Long walletId, @PathVariable Long delId) {
        service.delMemberToWalletId(walletId, delId);
        // wallet = Wallet().deleteMember(wallet_id, del_id)
        // return wallet
    }

    // proprietary

    @PutMapping("/{walletId}/proprietary/{proprietary}")
    public void updateProprietaryByWalletId(@PathVariable Long walletId, @PathVariable Long proprietary) {
        service.updateProprietaryByWalletId(walletId, proprietary);
        // wallet = Wallet().updateProprietary(wallet_id,proprietary)
        // return wallet
    }

}

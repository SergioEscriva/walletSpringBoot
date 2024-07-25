package dev.sergioescriva.wallet.services;

import java.util.List;

import dev.sergioescriva.wallet.models.Wallet;
import dev.sergioescriva.wallet.models.WalletUser;

public interface WalletService {

    List<Wallet> getWalletByPropietary(Long proprietaryId);

    Wallet getWalletByName(String walletName);

    void addWallet(String walletName, Long proprietaryId);

    Wallet updateWalletName(Long walletId, String nameNew);

    void delWalletById(Long walletId);

    Wallet getDescriptionById(Long walletId);

    void updateDescription(Long walletId, String description);

    void delDescriptionById(Long walletId);

    void updateShareById(Long walletId, Boolean share);

    List<WalletUser> getMembersById(Long walletId);

    void addMemberToWalletId(Long walletId, String membeName,
            Integer pin);

    void delMemberToWalletId(Long walletId, Long delId);

    void updateProprietaryByWalletId(Long walletId, Long proprietary);
}

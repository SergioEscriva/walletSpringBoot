package dev.sergioescriva.wallet.services;

import java.util.List;

import dev.sergioescriva.wallet.models.User;
import dev.sergioescriva.wallet.models.Wallet;

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

    List<User> getMembersById(Long walletId);

    void addMemberToWalletId(Long walletId, Long membeId,
            Integer pin);

    void delMemberToWalletId(Long walletId, Long delId);

    void updateProprietaryByWalletId(Long walletId, Long proprietary);
}

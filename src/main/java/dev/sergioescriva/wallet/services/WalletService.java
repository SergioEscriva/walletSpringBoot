package dev.sergioescriva.wallet.services;

public interface WalletService {

    String getWalletByPropietary(Long proprietaryId);

    String getWalletByName(String walletName);

    void addWallet(String walletName, Long proprietaryId);

    void updateWalletName(String nameOld, String nameNew);

    void delWalletById(Long walletId);

    void getDescriptionById(Long walletId);

    void updateDescription(Long walletId, String description);

    void delDescriptionById(Long walletId);

    void updateShareById(Long walletId, Boolean share);

    void getMembersById(Long walletId);

    void addMemberToWalletId(Long walletId, String membeName,
            Integer pin);

    void delMemberToWalletId(Long walletId, Long delId);

    void updateProprietaryByWalletId(Long walletId, Long proprietary);
}

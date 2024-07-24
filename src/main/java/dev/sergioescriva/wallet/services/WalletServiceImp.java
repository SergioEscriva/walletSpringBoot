package dev.sergioescriva.wallet.services;

import org.springframework.stereotype.Service;

@Service
public class WalletServiceImp implements WalletService {

    @Override
    public String getWalletByPropietary(Long proprietaryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWalletByPropietary'");
    }

    @Override
    public String getWalletByName(String walletName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWalletByName'");
    }

    @Override
    public void addWallet(String walletName, Long proprietaryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addWallet'");
    }

    @Override
    public void updateWalletName(String nameOld, String nameNew) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateWalletName'");
    }

    @Override
    public void delWalletById(Long walletId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delWalletById'");
    }

    @Override
    public void getDescriptionById(Long walletId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescriptionById'");
    }

    @Override
    public void updateDescription(Long walletId, String description) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDescription'");
    }

    @Override
    public void delDescriptionById(Long walletId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delDescriptionById'");
    }

    @Override
    public void updateShareById(Long walletId, Boolean share) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateShareById'");
    }

    @Override
    public void getMembersById(Long walletId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMembersById'");
    }

    @Override
    public void addMemberToWalletId(Long walletId, String membeName, Integer pin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMemberToWalletId'");
    }

    @Override
    public void delMemberToWalletId(Long walletId, Long delId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delMemberToWalletId'");
    }

    @Override
    public void updateProprietaryByWalletId(Long walletId, Long proprietary) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProprietaryByWalletId'");
    }

}

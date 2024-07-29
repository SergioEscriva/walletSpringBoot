package dev.sergioescriva.wallet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sergioescriva.wallet.models.User;
import dev.sergioescriva.wallet.models.Wallet;
import dev.sergioescriva.wallet.models.WalletUser;
import dev.sergioescriva.wallet.repositories.UserRepository;
import dev.sergioescriva.wallet.repositories.WalletRepository;
import dev.sergioescriva.wallet.repositories.WalletUserRepository;

@Service
public class WalletServiceImp implements WalletService {

    @Autowired
    WalletRepository repository;

    @Autowired
    WalletUserRepository membersRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Wallet> getWalletByPropietary(Long proprietaryId) {
        List<Wallet> walletList = new ArrayList<>();

        Iterable<Wallet> wallets = repository.findAll();

        for (Wallet wallet : wallets) {
            if (wallet.getProprietaryId() == proprietaryId) {
                walletList.add(wallet);

            }
        }
        return walletList;

    }

    @Override
    public Wallet getWalletByName(String walletName) {

        Iterable<Wallet> wallets = repository.findAll();

        for (Wallet wallet : wallets) {
            if (wallet.getName().equals(walletName)) {
                return wallet;

            }
        }
        return null;
    }

    @Override
    public void addWallet(String walletName, Long proprietaryId) {
        Iterable<Wallet> wallets = repository.findAll();
        List<Wallet> walletsList = new ArrayList<>();
        for (Wallet wallet : wallets) {
            walletsList.add(wallet);

        }

        if (!walletsList.contains(walletName)) {
            Wallet walletNew = new Wallet();
            walletNew.setName(walletName);
            walletNew.setProprietaryId(proprietaryId);
            repository.save(walletNew);

        }

    }

    @Override
    public Wallet updateWalletName(Long walletId, String nameNew) {
        Optional<Wallet> walletUpdate = repository.findById(walletId);
        if (walletUpdate.isPresent()) {
            Wallet walletEdit = walletUpdate.get();
            walletEdit.setName(nameNew);
            repository.save(walletEdit);
            return walletEdit;

        }
        return null;
    }

    @Override
    public void delWalletById(Long walletId) {
        Optional<Wallet> walletDel = repository.findById(walletId);
        if (walletDel.isPresent()) {
            repository.deleteById(walletId);

        }

    }

    @Override
    public Wallet getDescriptionById(Long walletId) {
        Optional<Wallet> walletUpdate = repository.findById(walletId);
        if (walletUpdate.isPresent()) {
            Wallet walletEdit = walletUpdate.get();
            walletEdit.getDescription();

            return walletEdit;

        }
        return null;
    }

    @Override
    public void updateDescription(Long walletId, String description) {
        Optional<Wallet> walletUpdate = repository.findById(walletId);
        if (walletUpdate.isPresent()) {
            Wallet walletEdit = walletUpdate.get();
            walletEdit.setDescription(description);

            repository.save(walletEdit);

        }

    }

    @Override
    public void delDescriptionById(Long walletId) {
        Optional<Wallet> walletUpdate = repository.findById(walletId);
        if (walletUpdate.isPresent()) {
            Wallet walletEdit = walletUpdate.get();
            walletEdit.setDescription("");

            repository.save(walletEdit);

        }
    }

    @Override
    public void updateShareById(Long walletId, Boolean share) {
        Optional<Wallet> walletUpdate = repository.findById(walletId);
        if (walletUpdate.isPresent()) {
            Wallet walletEdit = walletUpdate.get();
            walletEdit.setShare(share);

            repository.save(walletEdit);

        }

    }

    @Override
    public List<User> getMembersById(Long walletId) {
        Iterable<WalletUser> members = membersRepository.findAll();

        List<User> membersList = new ArrayList<>();
        for (WalletUser memberActual : members) {
            if (memberActual.getWalletId().equals(walletId)) {
                Optional<User> userAdd = userRepository.findById(memberActual.getUserId());
                membersList.add(userAdd.get());
            }

        }
        return membersList;

    }

    @Override
    public void addMemberToWalletId(Long walletId, Long memberId, Integer pin) {
        Iterable<WalletUser> members = membersRepository.findAll();

        List<WalletUser> membersList = new ArrayList<>();
        for (WalletUser memberActual : members) {
            if (memberActual.getWalletId().equals(walletId)) {
                membersList.add(memberActual);
            }

        }
        if (!membersList.contains(memberId)) {
            WalletUser newMember = new WalletUser();
            newMember.setWalletId(walletId);
            newMember.setUserId(memberId);
            membersRepository.save(newMember);
        }

    }

    @Override
    public void delMemberToWalletId(Long walletId, Long delId) {
        Iterable<WalletUser> members = membersRepository.findAll();

        List<WalletUser> membersList = new ArrayList<>();
        for (WalletUser memberActual : members) {
            if (memberActual.getWalletId().equals(walletId)) {
                membersList.add(memberActual);
            }

        }
        if (!membersList.contains(delId)) {
            WalletUser newMember = new WalletUser();
            newMember.setWalletId(walletId);
            newMember.setUserId(delId);
            membersRepository.delete(newMember);
        }

    }

    @Override
    public void updateProprietaryByWalletId(Long walletId, Long proprietary) {
        Optional<Wallet> wallet = repository.findById(walletId);
        if (wallet.isPresent()) {
            Wallet walletUpdate = wallet.get();
            walletUpdate.setProprietaryId(proprietary);
            repository.save(walletUpdate);
        }

    }

}

package dev.sergioescriva.wallet.services;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Override
    public void getUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public String getUserNameById(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserNameById'");
    }

    @Override
    public String getUserNamebyName(String userName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserNamebyName'");
    }

    @Override
    public void getPinByUserId(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPinByUserId'");
    }

    @Override
    public void updateNickname(String nameOld, String nameNew) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateNickname'");
    }

    @Override
    public void updateUserName(String nameOld, String nameNew) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUserName'");
    }

    @Override
    public void updateUserPinByUserId(Long pinOld, Long pinNew, Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUserPinByUserId'");
    }

    @Override
    public void addPinByUserName(String name, Long pin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPinByUserName'");
    }

    @Override
    public void delUserById(Long delId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delUserById'");
    }

}

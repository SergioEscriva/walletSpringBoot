package dev.sergioescriva.wallet.services;

import java.util.List;

import dev.sergioescriva.wallet.models.User;

public interface UserService {

    List<User> getUser();

    void addUser(String userName, String nickusername, String pin);

    User getUserNameById(Long userId);

    User getUserIdByName(String userName);

    User getPinByUserId(Long userId);

    void updateNicknameByUserId(Long userId, String nameNew);

    void updateUserNameByUserId(Long userId, String nameNew);

    void updateUserPinByUserId(String pinOld, String pinNew, Long userId);

    void addPinByUserId(Long userId, String pin);

    void delUserById(Long delId);
}

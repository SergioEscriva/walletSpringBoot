package dev.sergioescriva.wallet.services;

public interface UserService {

    void getUser();

    String getUserNameById(Long userId);

    String getUserNamebyName(String userName);

    void getPinByUserId(Long userId);

    void updateNickname(String nameOld, String nameNew);

    void updateUserName(String nameOld, String nameNew);

    void updateUserPinByUserId(Long pinOld, Long pinNew, Long userId);

    void addPinByUserName(String name, Long pin);

    void delUserById(Long delId);
}

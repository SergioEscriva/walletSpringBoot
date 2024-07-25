package dev.sergioescriva.wallet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sergioescriva.wallet.models.User;
import dev.sergioescriva.wallet.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public List<User> getUser() {
        List<User> userList = new ArrayList<>();
        Iterable<User> usersRepository = repository.findAll();

        for (User userRepository : usersRepository) {
            userList.add(userRepository);

        }

        return userList;
    }

    @Override
    public User getUserNameById(Long userId) {
        Optional<User> user = repository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public User getUserIdByName(String userName) {
        Iterable<User> users = repository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return user;
            }

        }

        return null;
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

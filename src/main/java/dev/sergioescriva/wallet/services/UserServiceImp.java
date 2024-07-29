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
    public List<User> getUsers() {
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
    public User getPinByUserId(Long userId) {
        Optional<User> user = repository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public void updateNicknameByUserId(Long userId, String nameNew) {
        Optional<User> user = repository.findById(userId);
        if (user.isPresent()) {
            User userActual = user.get();
            userActual.setNickname(nameNew);
            repository.save(userActual);
        }

    }

    @Override
    public void updateUserNameByUserId(Long userId, String nameNew) {
        Optional<User> user = repository.findById(userId);
        if (user.isPresent()) {
            User userActual = user.get();
            userActual.setUsername(nameNew);
            repository.save(userActual);
        }
    }

    @Override
    public void updateUserPinByUserId(String pinOld, String pinNew, Long userId) {
        Optional<User> user = repository.findById(userId);
        User userActual = user.get();
        if (userActual.getId().equals(userId) && userActual.getPassword().equals(pinOld)) {

            userActual.setPassword(pinNew);
            repository.save(userActual);
        }
    }

    @Override
    public void addPinByUserId(Long userId, String pin) {
        Optional<User> user = repository.findById(userId);
        User userActual = user.get();
        if (userActual.getId().equals(userId)) {

            userActual.setPassword(pin);
            repository.save(userActual);
        }
    }

    @Override
    public void delUserById(Long delId) {
        repository.deleteById(delId);
    }

    @Override
    public void addUserByName(String userName, String nickusername, String pin) {
        User newUser = new User();
        newUser.setUsername(userName);
        newUser.setNickname(nickusername);
        newUser.setPassword(pin);
        repository.save(newUser);
    }

}

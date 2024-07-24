package dev.sergioescriva.wallet.services;

import dev.sergioescriva.wallet.models.User;

public interface UserServiceImp {
    void save(User user);

    User findByUsername(String username);
}

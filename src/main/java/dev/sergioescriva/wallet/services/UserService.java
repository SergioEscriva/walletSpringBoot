package dev.sergioescriva.wallet.services;

import dev.sergioescriva.wallet.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

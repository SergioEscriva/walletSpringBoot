package dev.sergioescriva.wallet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sergioescriva.wallet.models.User;
import dev.sergioescriva.wallet.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService service;

    // members

    @GetMapping("/users")
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/id/{userId}")
    public User getUserNameById(@PathVariable Long userId) {
        User userName = service.getUserNameById(userId);
        return userName;// .getUsername();
    }

    @GetMapping("/name/{userName}")
    public Long getUserIdByName(@PathVariable String userName) {
        User userId = service.getUserIdByName(userName);
        return userId.getId();
    }

    @GetMapping("/nickname/{userId}")
    public String getNicknameByUserId(@PathVariable Long userId) {
        User userNickname = service.getUserNameById(userId);
        return userNickname.getNickname();
    }

    @GetMapping("/pin/{userId}")
    public User getPinByUserId(@PathVariable Long userId) {
        return service.getPinByUserId(userId);
    }

    @PutMapping("/nickname/{nameOld}/{nameNew}")
    public void updateNickname(@PathVariable Long userId, @PathVariable String nameNew) {
        service.updateNicknameByUserId(userId, nameNew);

    }

    @PutMapping("/{userId}/{nameNew}")
    public void updateUserName(@PathVariable Long userId, @PathVariable String nameNew) {

        service.updateUserNameByUserId(userId, nameNew);

    }

    @PutMapping("/pin/{pinOld}/{pinNew}/{userId}")

    public void updateUserPinByUserId(@PathVariable String pinOld, @PathVariable String pinNew,
            @PathVariable Long userId) {
        service.updateUserPinByUserId(pinOld, pinNew, userId);
    }

    @PostMapping("/pin/{userId}/{pin}")
    public void addPinByUserName(@PathVariable Long userId, @PathVariable String pin) {
        service.addPinByUserId(userId, pin);
    }

    @DeleteMapping("/{delId}")
    public void delUserById(@PathVariable Long delId) {
        service.delUserById(delId);
    }

    @PostMapping("/{userName}/{pin}")
    public void addUserByName(@PathVariable String userName, @PathVariable String pin) {
        service.addUserByName(userName, userName, pin);
    }
}

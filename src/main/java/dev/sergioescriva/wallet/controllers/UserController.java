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

    @GetMapping
    public List<User> getUser() {
        return service.getUser();
    }

    @GetMapping("/name/{userId}")
    public User getUserNameById(@PathVariable Long userId) {

        return service.getUserNameById(userId);
    }

    @GetMapping("/name/{userName}")
    public User getUserIdByName(@PathVariable String userName) {

        return service.getUserIdByName(userName);
    }

    @GetMapping("/pin/{userId}")
    public User getPinByUserId(@PathVariable Long userId) {
        return service.getPinByUserId(userId);
    }

    @PutMapping("/user/nickname/{nameOld}/{nameNew}")
    public void updateNickname(@PathVariable Long userId, @PathVariable String nameNew) {
        service.updateNicknameByUserId(userId, nameNew);

    }

    @PutMapping("/user/{userId}/{nameNew}")
    public void updateUserName(@PathVariable Long userId, @PathVariable String nameNew) {

        service.updateUserNameByUserId(userId, nameNew);

    }

    @PutMapping("/user/pin/{pinOld}/{pinNew}/{userId}")

    public void updateUserPinByUserId(@PathVariable String pinOld, @PathVariable String pinNew,
            @PathVariable Long userId) {
        service.updateUserPinByUserId(pinOld, pinNew, userId);
    }

    @PostMapping("/user/{userId}/{pin}")
    public void addPinByUserName(@PathVariable Long userId, @PathVariable String pin) {
        service.addPinByUserId(userId, pin);
    }

    @DeleteMapping("/user/{delId}")
    public void delUserById(@PathVariable Long delId) {
        service.delUserById(delId);
    }
}

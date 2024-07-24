package dev.sergioescriva.wallet.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    // members

    @GetMapping("/users")
    public void getUser() {
        // user = User().users()
        // return user
    }

    @GetMapping("/name/{userId}")
    public String getUserNameById(@PathVariable Long userId) {

        // user = User().userIdName(user_id)

        return new String();
    }

    @GetMapping("/user/{userName}")
    public String getUserNamebyName(@PathVariable String userName) {
        // user = User().userNameId(user_name)
        // return user
        return new String();
    }

    @GetMapping("/pin/{userId}")
    public void getPinByUserId(@PathVariable Long userId) {
        // user = User().pin(user_id)
        // return user
    }

    @PutMapping("/user/nickname/{nameOld}/{nameNew}")
    public void updateNickname(@PathVariable String nameOld, @PathVariable String nameNew) {
        // user = User().update_nickname(name_old, name_new)
        // return user
    }

    @PutMapping("/user/{nameOld}/{nameNew}")
    public void updateUserName(@PathVariable String nameOld, @PathVariable String nameNew) {
        // user = User().update_user(name_old, name_new)
        // return user
    }

    @PutMapping("/user/pin/{pinOld}/{pinNew}/{userId}")

    public void updateUserPinByUserId(@PathVariable Long pinOld, @PathVariable Long pinNew, @PathVariable Long userId) {
        // user = User().update_pin(pin_old, pin_new, user_id)
        // return user
    }

    @PostMapping("/user/{name}/{pin}")
    public void addPinByUserName(@PathVariable String name, @PathVariable Long pin) {
        // user = User().new(name, pin)
        // return user
    }

    @DeleteMapping("/user/{delId}")
    public void delUserById(@PathVariable Long delId) {
        // user = User().delete(del_id)
        // return user
    }
}

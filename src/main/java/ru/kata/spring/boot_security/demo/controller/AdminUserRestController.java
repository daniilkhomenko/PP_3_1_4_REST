package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminUserRestController {
    private final UserService userService;

    @GetMapping("/user")
    public User getUserByUsername (Principal principal) {
        return userService.findUserByEmail(principal.getName());
    }

    @GetMapping("/admin")
    public List<User> userList(){
        return userService.getAllUsers();
    }

//    @PostMapping("/admin")
//    @PutMapping("/admin")
    @RequestMapping(value = "/admin", method = {RequestMethod.PUT, RequestMethod.POST})
    public User createUpdateUser(@RequestBody User user){
        userService.createUpdate(user);
        return user;
    }

    @DeleteMapping("/admin/{id}")
    public List<User> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return userService.getAllUsers();
    }
}
package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> getUserByUsername (Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> userList(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

//    @PostMapping("/admin")
//    @PutMapping("/admin")
    @RequestMapping(value = "/admin", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<User> createUpdateUser(@RequestBody User user){
        userService.createUpdate(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Long id) {
            userService.delete(id);
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
package com.example.eatgo.interfaces;

import com.example.eatgo.application.UserService;
import com.example.eatgo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {
    //1. User List
    //2. User create -> 회원가입
    //3. User update
    //4. User delete -> level:0 => 아무 것도 못함.
    //(1: customer, 2: restaurant owner, 3: admin)

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> list() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(
            @RequestBody User resource) throws URISyntaxException {
        User user = User.builder()
                .email(resource.getEmail())
                .name(resource.getName())
                .level(1)
                .build();
        user = userService.addUser(user);
        URI uri = new URI("/users/" + user.getId());

        return ResponseEntity.created(uri).body("{}");
    }

    @PatchMapping("/users/{userId}")
    public String update(
            @PathVariable("userId") Long userId,
            @RequestBody User resource) throws URISyntaxException {

        userService.updateUser(userId, resource);
        return "{}";
    }

    @DeleteMapping("/users/{userId}")
    public String delete(
            @PathVariable("userId") Long userId) {

        userService.deleteUser(userId);
        return "{}";
    }

}


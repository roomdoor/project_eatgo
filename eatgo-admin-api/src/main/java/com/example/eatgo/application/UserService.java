package com.example.eatgo.application;

import com.example.eatgo.domain.User;
import com.example.eatgo.domain.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long userId, User resource) {
        //TODO: restaurantService 의 예외 처리 참고.
        User user = userRepository.findById(userId).orElse(null);

        user.setName(resource.getName());
        user.setEmail(resource.getEmail());
        user.setLevel(resource.getLevel());

        return user;
    }

    public User deleteUser(Long userId) {

        User user = userRepository.findById(userId).orElse(null);
        user.deativate();
        return user;
    }
}

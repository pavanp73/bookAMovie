package com.lld.bookamovie.services;

import com.lld.bookamovie.models.User;
import com.lld.bookamovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User singUp(String name, String email, String password) {
        /*
        1. Check if user already exists in the db with the give email
        2. If yes, ask the user to login
        3. If not, create a new user object with given details
        4. Save it to DB
         */
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        user = userRepository.save(user);

        return user;
    }

    public User login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
         if (optionalUser.isEmpty()) {
             throw new RuntimeException("User not found");
         }

         User user = optionalUser.get();

         // Compare the password
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            // Login successful
            return user;
        }
        return null;
    }
}

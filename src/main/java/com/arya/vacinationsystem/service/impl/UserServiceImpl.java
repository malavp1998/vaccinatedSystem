package com.arya.vacinationsystem.service.impl;

import com.arya.vacinationsystem.entity.User;
import com.arya.vacinationsystem.repository.UserRepository;
import com.arya.vacinationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(long aadharNumber) {
        try {
            Optional<User> user = userRepository.findById(aadharNumber);
            if (!user.isPresent()) {
                User newUser = new User(aadharNumber, false);
                userRepository.save(newUser);
                return newUser;
            }

        } catch (Exception e) {
            System.out.println("Exception in registerUser " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public boolean isUserVaccinated(long aadharNumber) {
        try {
            User user = userRepository.findById(aadharNumber).get();
            if (user != null) {
                return user.isVaccinated();
            }
        } catch (Exception e) {
            System.out.println("Exception in isUserVaccinated " + e.getMessage());
            throw e;
        }
        return false;
    }

    @Override
    public User marKVaccinate(long aadharNumber) {
        Optional<User> user = userRepository.findById(aadharNumber);
        user.get().setVaccinated(true);
        return userRepository.save(user.get());
    }
}

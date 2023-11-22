package com.arya.vacinationsystem.service;

import com.arya.vacinationsystem.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registerUser(long aadharNumber);
    boolean isUserVaccinated(long aadharNumber);

    User marKVaccinate(long aadharNumber);

}

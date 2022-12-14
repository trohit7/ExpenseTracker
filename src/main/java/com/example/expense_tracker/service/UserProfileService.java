package com.example.expense_tracker.service;


import com.example.expense_tracker.dto.user.UserPro;
import com.example.expense_tracker.model.UserProfile;
import com.example.expense_tracker.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public void addProfile(@Valid UserPro userProfile) {
        userProfileRepository.save(userProfile);
    }

    public List<UserProfile> listProfiles(){
        return userProfileRepository.findAll();
    }

}
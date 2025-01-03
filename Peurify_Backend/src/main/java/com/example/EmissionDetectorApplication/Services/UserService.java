package com.example.EmissionDetectorApplication.Services;

import com.example.EmissionDetectorApplication.Entity.User;
import com.example.EmissionDetectorApplication.Exception.ResourceNotFoundException;
import com.example.EmissionDetectorApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    public User getUserById(Long id) {
        return  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
    }
    // Create or Update User
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Long authenticateUser(String phoneNumber, String password) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null && user.getPassword().equals(password)) {
            return user.getId();
        }
        return null;
    }


    // Delete user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setRewards(updatedUser.getRewards());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
     }




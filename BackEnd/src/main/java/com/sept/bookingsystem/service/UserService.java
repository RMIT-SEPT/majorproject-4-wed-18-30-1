package com.sept.bookingsystem.service;

import com.sept.bookingsystem.Repositories.UserRepository;
import com.sept.bookingsystem.exceptions.UserException;
import com.sept.bookingsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user){

        try {
            user.setEmail(user.getEmail().toUpperCase());
            return userRepository.save(user);
        } catch (Exception e)
        {
            throw new UserException("User Email '" + user.getEmail().toUpperCase()+"' already exists");
        }
    }

    public User findByEmail(String Email){

        User user = userRepository.findByEmail(Email.toUpperCase());

        if (user == null){
            throw new UserException("User Email '"+Email+"' does not exist");
        }

        return user;
    }

    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUserByEmail(String Email){
        User user = userRepository.findByEmail(Email.toUpperCase());

        if (user == null){
            throw new UserException("Cannot find User with email: '" + Email + "'. This User does not exist");
        }

        userRepository.delete(user);
    }
}

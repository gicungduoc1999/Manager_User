package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.request.UserRequest;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addUser(UserRequest userRequest) {
        User user = userRepository.findFirstById(userRequest.getUserId());
        if (!ObjectUtils.isEmpty(user)) {
            // throw message (ExceptionHandler)
            return;
        }
        //add
        userRepository.save(user);
    }
}

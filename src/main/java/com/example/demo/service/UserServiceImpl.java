package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.request.UserRequest;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserRequest userRequest) throws SQLException {
        User user = userRepository.findUserById(userRequest.getUserId());
        if (!ObjectUtils.isEmpty(user)) {
            // throw message (ExceptionHandler)
            return;
        }
        //add
       // userRepository.save(user);
    }
}

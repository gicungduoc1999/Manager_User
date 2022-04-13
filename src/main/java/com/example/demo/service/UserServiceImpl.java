package com.example.demo.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.model.User;
import com.example.demo.request.UserRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserRequest userRequest) throws SQLException {
        User user = userRepository.findUserById(userRequest.getUserId());
        if (!ObjectUtils.isEmpty(user)) {
            throw new BusinessException("User exists");
        }
        userRepository.addUser(userRequest);
    }

    @Override
    public void deleteUser(Long userId) throws SQLException {
        User user = userRepository.findUserById(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException("User not found");
        }
        userRepository.deleteUser(userId);
    }

    @Override
    public void editUser(UserRequest userRequest) throws SQLException {
        User user = userRepository.findUserById(userRequest.getUserId());
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException("User not found");
        }
        userRepository.editUser(userRequest);
    }

    @Override
    public List<User> searchUser(UserSearchRequest userSearchRequest) throws SQLException {
        List<User> users = userRepository.searchUser(userSearchRequest);
        if (ObjectUtils.isEmpty(users)) {
            throw new BusinessException("User not found");
        }
        return users;
    }

    @Override
    public void add5tr() throws SQLException {
        userRepository.add5tr();
    }
}

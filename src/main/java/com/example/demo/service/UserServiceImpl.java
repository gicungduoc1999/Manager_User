package com.example.demo.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.model.User;
import com.example.demo.request.UserRequest;
import com.example.demo.repository.UserRepository;
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
        validateUser(userRequest);
        User user = userRepository.findUserById(userRequest.getUserId());
        if (!ObjectUtils.isEmpty(user)) {
            throw new BusinessException(902, "User exists");
        }
        userRepository.addUser(userRequest);
    }

    private void validateUser(UserRequest userRequest) {
        if (ObjectUtils.isEmpty(userRequest.getName())) {
            throw new BusinessException(900, "Name is mandatory");
        }
        if (ObjectUtils.isEmpty(userRequest.getAddress())) {
            throw new BusinessException(900, "Address is mandatory");
        }
        if (userRequest.getAge() < 1 || userRequest.getAge() > 100) {
            throw new BusinessException(900, "Age must be greater than 1 and less than 100");
        }
    }

    @Override
    public void deleteUser(Long userId) throws SQLException {
        User user = userRepository.findUserById(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException(404, "User not found");
        }
        userRepository.deleteUser(userId);
    }

    @Override
    public void editUser(UserRequest userRequest) throws SQLException {
        validateUser(userRequest);
        User user = userRepository.findUserById(userRequest.getUserId());
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException(404, "User not found");
        }
        userRepository.editUser(userRequest);
    }

    @Override
    public List<User> searchUser(UserRequest userRequest) throws SQLException {
        List<User> users = userRepository.searchUser(userRequest);
        if (ObjectUtils.isEmpty(users)) {
            throw new BusinessException(404, "User not found");
        }
        return users;
    }

    @Override
    public void add5tr() throws SQLException {
        userRepository.add5tr();
    }
}

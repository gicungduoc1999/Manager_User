package com.example.demo.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.model.User;
import com.example.demo.request.UserRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.EntityCustomResponse;
import com.example.demo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public EntityCustomResponse addUser(UserRequest userRequest) {
        try {
            validateUser(userRequest);
            User user = userRepository.findUserById(userRequest.getUserId());
            if (!ObjectUtils.isEmpty(user)) {
                throw new BusinessException(902, "User exists");
            }
            userRepository.addUser(userRequest);

        } catch (BusinessException businessException) {
            return new EntityCustomResponse(0, businessException.getMessage(), businessException.getStatusCode(), List.of());
        } catch (SQLException E) {
            return new EntityCustomResponse(0, "Error when querying data into database", 901, List.of());
        } catch (Exception exception) {
            return new EntityCustomResponse(0, "Error system ", 500, List.of());
        }
        return new EntityCustomResponse(1, "Add success", 200, List.of());
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
    public EntityCustomResponse deleteUser(Long userId) {
        try {
            User user = userRepository.findUserById(userId);
            if (ObjectUtils.isEmpty(user)) {
                throw new BusinessException(404, "User not found");
            }
            userRepository.deleteUser(userId);

        } catch (BusinessException businessException) {
            return new EntityCustomResponse(0, businessException.getMessage(), businessException.getStatusCode(), List.of());
        } catch (SQLException E) {
            return new EntityCustomResponse(0, "Error when querying data into database", 901, List.of());
        } catch (Exception exception) {
            return new EntityCustomResponse(0, "Error system ", 500, List.of());
        }
        return new EntityCustomResponse(1, "Delete successes", 200, List.of());
    }

    @Override
    public EntityCustomResponse editUser(UserRequest userRequest) {
        try {
            validateUser(userRequest);
            User user = userRepository.findUserById(userRequest.getUserId());
            if (ObjectUtils.isEmpty(user)) {
                throw new BusinessException(404, "User not found");
            }
            userRepository.editUser(userRequest);

        } catch (BusinessException businessException) {
            return new EntityCustomResponse(0, businessException.getMessage(), businessException.getStatusCode(), List.of());
        } catch (SQLException E) {
            return new EntityCustomResponse(0, "Error when querying data into database", 901, List.of());
        } catch (Exception exception) {
            return new EntityCustomResponse(0, "Error system ", 500, List.of());
        }
        return new EntityCustomResponse(1, "Edit successes", 200, List.of());
    }

    @Override
    public EntityCustomResponse searchUser(UserRequest userRequest) {
        List<UserResponse> userResponses;
        try {
            List<User> users = userRepository.searchUser(userRequest);
            if (ObjectUtils.isEmpty(users)) {
                throw new BusinessException(404, "User not found");
            }
            userResponses = users.stream().map(user -> UserResponse.toUserResponse(user)).collect(Collectors.toList());

        } catch (BusinessException businessException) {
            return new EntityCustomResponse(0, businessException.getMessage(), businessException.getStatusCode(), List.of());
        } catch (SQLException E) {
            return new EntityCustomResponse(0, "Error when querying data into database", 901, List.of());
        } catch (Exception exception) {
            return new EntityCustomResponse(0, "Error system ", 500, List.of());
        }
        return new EntityCustomResponse(1, "User information", 200, Collections.singletonList(userResponses));
    }

    @Override
    public EntityCustomResponse add5tr() {
        try {
            userRepository.add5tr();
        } catch (SQLException E) {
            return new EntityCustomResponse(0, "Error system ", 404, List.of());
        }
        return new EntityCustomResponse(1, "add 5000000 successes", 200, List.of());
    }

    @Override
    public EntityCustomResponse searchUserByName(UserRequest userRequest) {
        List<UserResponse> userResponses;
        try {
            List<User> users = userRepository.searchUserByName(userRequest);
            if (ObjectUtils.isEmpty(users)) {
                throw new BusinessException(404, "User not found");
            }
            userResponses = users.stream().map(user -> UserResponse.toUserResponse(user)).collect(Collectors.toList());

        } catch (BusinessException businessException) {
            return new EntityCustomResponse(0, businessException.getMessage(), businessException.getStatusCode(), List.of());
        } catch (SQLException E) {
            return new EntityCustomResponse(0, "Error when querying data into database", 901, List.of());
        } catch (Exception exception) {
            return new EntityCustomResponse(0, "Error system ", 500, List.of());
        }
        return new EntityCustomResponse(1, "User information", 200, Collections.singletonList(userResponses));
    }

    @Override
    public EntityCustomResponse addMoney(Long id, Long numberMoney) {
        try {
            userRepository.addMoney(id, numberMoney);
        } catch (BusinessException businessException) {
            return new EntityCustomResponse(0, businessException.getMessage(), businessException.getStatusCode(), List.of());
        } catch (SQLException E) {
            return new EntityCustomResponse(0, "Error when querying data into database", 901, List.of());
        } catch (Exception exception) {
            return new EntityCustomResponse(0, "Error system ", 500, List.of());
        }
        return new EntityCustomResponse(1, "Add success " + numberMoney + " $", 200, List.of());
    }

    @Override
    public EntityCustomResponse transferMoney(Long userIdA, Long userIdB, Long numberMoney) {
        try {
            int rowCount = userRepository.transferMoney(userIdA, userIdB, numberMoney);
            if (rowCount == 0) {
                throw new BusinessException(900, "UserA not enough money");
            }
            userRepository.addMoney(userIdB, numberMoney);
        } catch (BusinessException businessException) {
            return new EntityCustomResponse(0, businessException.getMessage(), businessException.getStatusCode(), List.of());
        } catch (SQLException E) {
            return new EntityCustomResponse(0, "Error when querying data into database", 901, List.of());
        } catch (Exception exception) {
            return new EntityCustomResponse(0, "Error system ", 500, List.of());
        }
        return new EntityCustomResponse(1, "Transfer success for user B " + numberMoney + " $", 200, List.of());
    }

    private void validateTransferMoney(Long oldMoneyUserA, Long oldMoneyUserB, Long numberMoney) throws BusinessException {
        if (ObjectUtils.isEmpty(oldMoneyUserA) || ObjectUtils.isEmpty(oldMoneyUserB)) {
            throw new BusinessException(404, "User not found");
        }
        if (oldMoneyUserA <= 0) {
            throw new BusinessException(900, "UserA don't have money");
        }
        if (oldMoneyUserA < numberMoney) {
            throw new BusinessException(900, "UserA not enough money (UserA : " + oldMoneyUserA + ")");
        }
    }
}

package com.example.demo.repository;


import com.example.demo.model.User;
import com.example.demo.repository.connect.ConnectionUtils;
import com.example.demo.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findUserById(Long userId) throws SQLException {
        User user = new User();
        String sql = "select * from [User] where id=?";
        Connection con = null;
        PreparedStatement pre = null;
        ConnectionUtils connectionUtils=new ConnectionUtils();
        try {
            con = connectionUtils.getConnection();
            pre = con.prepareStatement(sql);
            pre.setLong(1, userId);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("Name");
                String role = resultSet.getString("Role");
                String address = resultSet.getString("Address");
                Long age = resultSet.getLong("Age");
                user = new User(id, name, role, address, age);
            }
        } catch (Exception e) {
            con.close();
            pre.close();

        } finally {
            con.close();
            pre.close();
        }
        return user;

    }

    @Override
    public void addUser(UserRequest userRequest) {

    }


}

package com.example.demo.repository;


import com.example.demo.model.User;
import com.example.demo.repository.connect.ConnectionUtils;
import com.example.demo.request.UserRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findUserById(Long userId) throws SQLException {
        User user = null;
        String sql = "select * from [User] where id=?";
        Connection con = null;
        PreparedStatement pre = null;
        ConnectionUtils connectionUtils = ConnectionUtils.getInstance();
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
    public void addUser(UserRequest userRequest) throws SQLException {
        String sql = "INSERT INTO [dbo].[User]\n" +
                "           ([Role]\n" +
                "           ,[Name]\n" +
                "           ,[Address]\n" +
                "           ,[Age])\n" +
                "     VALUES\n" +
                "           (?\n" +
                "           ,?\n" +
                "           ,?\n" +
                "           ,?)";
        Connection con = null;
        PreparedStatement pre = null;
        ConnectionUtils connectionUtils = ConnectionUtils.getInstance();
        try {
            con = connectionUtils.getConnection();
            pre = con.prepareStatement(sql);
            con.setAutoCommit(false);
            pre.setString(1, userRequest.getRole());
            pre.setString(2, userRequest.getName());
            pre.setString(3, userRequest.getAddress());
            pre.setLong(4, userRequest.getAge());
            pre.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            con.close();
            pre.close();

        } finally {
            con.close();
            pre.close();
        }
    }

    @Override
    public void deleteUser(Long userId) throws SQLException {
        String sql = "delete [User] where id=?";
        Connection con = null;
        PreparedStatement pre = null;
        ConnectionUtils connectionUtils = ConnectionUtils.getInstance();
        try {
            con = connectionUtils.getConnection();
            pre = con.prepareStatement(sql);
            con.setAutoCommit(false);
            pre.setLong(1, userId);
            pre.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            con.close();
            pre.close();

        } finally {
            con.close();
            pre.close();
        }
    }

    @Override
    public void editUser(UserRequest userRequest) throws SQLException {
        String sql = "UPDATE [dbo].[User]\n" +
                "   SET [Role] = ? \n" +
                "      ,[Name] = ? \n" +
                "      ,[Address] = ? \n" +
                "      ,[Age] = ? \n" +
                " WHERE id=?";
        Connection con = null;
        PreparedStatement pre = null;
        ConnectionUtils connectionUtils = ConnectionUtils.getInstance();
        try {
            con = connectionUtils.getConnection();
            pre = con.prepareStatement(sql);
            con.setAutoCommit(false);
            pre.setString(1, userRequest.getRole());
            pre.setString(2, userRequest.getName());
            pre.setString(3, userRequest.getAddress());
            pre.setLong(4, userRequest.getAge());
            pre.setLong(5, userRequest.getUserId());
            pre.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            con.close();
            pre.close();

        } finally {
            con.close();
            pre.close();
        }
    }

    @Override
    public List<User> searchUser(UserRequest userRequest) throws SQLException {
        List<User> users = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        ConnectionUtils connectionUtils = ConnectionUtils.getInstance();
        sql.append(" select * ");
        sql.append(" from [User] ");
        sql.append(" where (1=1) ");
        Connection con = null;
        PreparedStatement pre = null;
        if (!ObjectUtils.isEmpty(userRequest.getUserId())) {
            sql.append(" and id = ");
            sql.append(userRequest.getUserId());
        }
        if (!ObjectUtils.isEmpty(userRequest.getAddress())) {
            sql.append(" and [Address] = '");
            sql.append(userRequest.getAddress());
            sql.append("' ");

        }
        if (!ObjectUtils.isEmpty(userRequest.getName())) {
            sql.append(" and [Name] = '");
            sql.append(userRequest.getName());
            sql.append("' ");
        }
        sql.append(" order by Name ");
        try {
            con = connectionUtils.getConnection();
            pre = con.prepareStatement(sql.toString());
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("Name");
                String role = resultSet.getString("Role");
                String address = resultSet.getString("Address");
                Long age = resultSet.getLong("Age");
                User user = new User(id, name, role, address, age);
                users.add(user);
            }
        } catch (Exception e) {
            con.close();
            pre.close();

        } finally {
            con.close();
            pre.close();
        }
        return users;
    }

    @Override
    public void add5tr() throws SQLException {
        String sql = "INSERT INTO [dbo].[User]\n" +
                "           ([Role]\n" +
                "           ,[Name]\n" +
                "           ,[Address]\n" +
                "           ,[Age])\n" +
                "     VALUES\n" +
                "           (?\n" +
                "           ,?\n" +
                "           ,?\n" +
                "           ,?)";
        Connection con = null;
        PreparedStatement pre = null;
        ConnectionUtils connectionUtils = ConnectionUtils.getInstance();
        try {
            con = connectionUtils.getConnection();
            pre = con.prepareStatement(sql);
            con.setAutoCommit(false);
            Long count = 0L;
            while (true) {
                if (count == 5000000) {
                    break;
                }
                if (count % 200 == 0) {
                    pre.executeBatch();
                    con.commit();
                }
                pre.setString(1, "ADMIN " + count);
                pre.setString(2, "Mr D " + count);
                pre.setString(3, "Hoa Binh " + count);
                pre.setLong(4, count);
                pre.addBatch();
                count++;
            }
            pre.executeBatch();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            con.close();
            pre.close();

        } finally {
            con.close();
            pre.close();
        }
    }

    @Override
    public List<User> searchUserByName(UserRequest userRequest) throws SQLException {
        List<User> users = new ArrayList<>();
        ConnectionUtils connectionUtils = ConnectionUtils.getInstance();
        Connection con = null;
        PreparedStatement pre = null;
        String sql = "";

        //Index and search full text
        if (userRequest.getOperation() == 1) {
            sql = "select * from [User] where Name like '" + userRequest.getName() + "%' ";
        } else if (userRequest.getOperation() == 2) {
            sql = "select * from [User] where FREETEXT(Name,'" + userRequest.getName() + "') ";
        } else {
            sql = "select * from [User] where Name = '" + userRequest.getName() + "' ";
        }

        try {
            con = connectionUtils.getConnection();
            pre = con.prepareStatement(sql);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("Name");
                String role = resultSet.getString("Role");
                String address = resultSet.getString("Address");
                Long age = resultSet.getLong("Age");
                User user = new User(id, name, role, address, age);
                users.add(user);
            }
        } catch (Exception e) {
            con.close();
            pre.close();

        } finally {
            con.close();
            pre.close();
        }
        return users;
    }

    @Override
    public void updateMoney(Long id, Long numberMoney) throws SQLException {
        ConnectionUtils connectionUtils = ConnectionUtils.getInstance();
        Connection con = null;
        PreparedStatement pre = null;
        String sql = "update [User] set Money = ?\n" +
                "where id = ?";
        try {
            con = connectionUtils.getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement(sql);
            pre.setLong(1, numberMoney);
            pre.setLong(2, id);
            pre.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            con.close();
            pre.close();
            throw e;
        } finally {
            con.close();
            pre.close();
        }
    }

    @Override
    public Long getMoney(Long id) throws SQLException {
        ConnectionUtils connectionUtils = ConnectionUtils.getInstance();
        Connection con = null;
        PreparedStatement pre = null;
        String sql = "select Money from [User] where id = ?";
        Long money = null;

        try {
            con = connectionUtils.getConnection();
            pre = con.prepareStatement(sql);
            pre.setLong(1, id);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                money = resultSet.getLong("Money");
            }
        } catch (Exception e) {
            con.close();
            pre.close();
            throw e;
        } finally {
            con.close();
            pre.close();
        }
        return money;
    }
}

package com.revature.ers.dao.impl;

import com.revature.ers.dao.UserDao;
import com.revature.ers.models.Users;
import com.revature.ers.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = ConnectionUtils.getInstance().getConnection();

            stmt = connection.createStatement();

            String sql = "SELECT * FROM USERS";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Users a = new Users();
                a.setId(rs.getLong("user_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setFirstName(rs.getString("firstName"));
                a.setLastName(rs.getString("lastName"));
                a.setEmail(rs.getString("email"));
                a.setUserRole(rs.getString("userRole"));

                users.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    @Override
    public Users getUser(int userid) {
        Users user = new Users();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConnectionUtils.getInstance().getConnection();

            String sql = "SELECT * FROM users WHERE user_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, userid);


            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                user.setId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setUserRole(rs.getString("userRole"));
            }else {
                user.setId(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public Users getUserByName(String username) {
        Users user = new Users();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConnectionUtils.getInstance().getConnection();

            String sql = "SELECT * FROM users WHERE username = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);


            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                user.setId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setUserRole(rs.getString("userRole"));
            }else {
                user.setId(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    @Override
    public void deleteUser(int userid) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConnectionUtils.getInstance().getConnection();

            String sql = "DELETE FROM users WHERE user_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, userid);

            stmt.executeUpdate();
            System.out.println("User(s) Deleted!!");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Users saveUser(Users userToRegister) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = ConnectionUtils.getInstance().getConnection();
            String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?)";

            // Setup PreparedStatement
            stmt = connection.prepareStatement(sql);

            // Add parameters from user into PreparedStatement
            stmt.setLong(1, userToRegister.getId());
            stmt.setString(2, userToRegister.getUsername());
            stmt.setString(3, userToRegister.getPassword());
            stmt.setString(4, userToRegister.getFirstName());
            stmt.setString(5, userToRegister.getLastName());
            stmt.setString(6, userToRegister.getEmail());
            stmt.setString(7, userToRegister.getUserRole());


            success = stmt.executeUpdate();
            return userToRegister;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            // then update didn't occur, throw an exception
            throw new Exception("Insert user failed: " + userToRegister);
        }

        return userToRegister;
    }


    @Override
    public void updateUserRole(Users userRoleToUpdate) throws Exception {

        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = ConnectionUtils.getInstance().getConnection();
            String sql = "update users set userRole = ? where user_id = ?";


            // Setup PreparedStatement
            stmt = connection.prepareStatement(sql);

            // Add parameters from user into PreparedStatement
            stmt.setString(1, userRoleToUpdate.getUserRole());
            stmt.setLong(2, userRoleToUpdate.getId());


            success = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            // then update didn't occur, throw an exception
            throw new Exception("Failed to Update Users Role > : " + userRoleToUpdate);
        }
    }

    @Override
    public void updateUser(Users userToUpdate) throws Exception {

        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = ConnectionUtils.getInstance().getConnection();
            String sql = "update users set username = ?,password = ?,firstName = ?, lastName = ?,email = ?, userRole = ? where user_id = ?";


            // Setup PreparedStatement
            stmt = connection.prepareStatement(sql);

            // Add parameters from user into PreparedStatement
            stmt.setString(1, userToUpdate.getUsername());
            stmt.setString(2, userToUpdate.getPassword());
            stmt.setString(3, userToUpdate.getFirstName());
            stmt.setString(4, userToUpdate.getLastName());
            stmt.setString(5, userToUpdate.getEmail());
            stmt.setString(6, userToUpdate.getUserRole());
            stmt.setLong(7, userToUpdate.getId());

            success = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            // then update didn't occur, throw an exception
            throw new Exception("Failed to Update Users > : " + userToUpdate);
        }
    }



}
package com.revature.ers.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.revature.ers.dao.UserDao;
import com.revature.ers.exceptions.InvalidCredentialsException;
import com.revature.ers.log.LogThis;
import com.revature.ers.models.Users;
import com.revature.ers.utils.ConnectionUtils;

public class UserService {
    //Call DAO method
    UserDao dao = ConnectionUtils.getUserDao();


    public List<Users> getAllUsers(){
        return dao.getAllUsers();
    }
    public Users getUser(int userId){
        Users user = dao.getUser(userId);
        return user;
    }
    public Users getUserByName(String userName) throws InvalidCredentialsException {
        try {
            Users user = dao.getUserByName(userName);
            return user;
        }catch (NullPointerException e){
            throw new InvalidCredentialsException();
        }

    }
    public Users loginUser(String username, String password) throws InvalidCredentialsException {
        Users user = dao.getUserByName(username);
        if (user.getPassword().equals(password)){
            LogThis.logger.info("User: " + username + " was logged in");
            return user;
        }else {
            throw new InvalidCredentialsException();
        }
    }


    public void updateUserRole(Users user) throws Exception {
        dao.updateUserRole(user);
        LogThis.logger.warn("User: " + user.getUsername() + " 's Role has been changed to "+ user.getUserRole());
    }

    public Users updateUser(Users user) throws Exception {
        System.out.println("USerSerive UpdateUser Was Called!! with user > " + user);
        try {
            dao.updateUser(user);
            LogThis.logger.warn("User: " + user.getUsername() + " has been updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUser(int userId){
        dao.deleteUser(userId);
    }

    public Users saveUser(Users userToSave){
        try {
            dao.saveUser(userToSave);
            Logging.logger.info("User: " + userToSave.getUsername() + " was Created");
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            System.out.println("ID is already in use");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem creating the User at this time");
        }
        return userToSave;
    }


}
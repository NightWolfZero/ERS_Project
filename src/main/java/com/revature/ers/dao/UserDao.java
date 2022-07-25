package com.revature.ers.dao;

import java.util.List;

import com.revature.ers.models.Users;

public interface UserDao {
	 List<Users> getAllUsers();

	    Users getUser(int userid);

	    Users getUserByName(String username);

	    void deleteUser(int userid);

	    Users saveUser(Users userToRegister) throws Exception;

	    void updateUserRole(Users userRoleToUpdate) throws Exception;

	    void updateUser(Users userRoleToUpdate) throws Exception;






	}
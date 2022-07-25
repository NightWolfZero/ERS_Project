package com.revature.ers.models;

public class Users {

	 private long user_id;
	    private String username;
	    private String password;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private UserRole userRole;

	    public Users(String username, String password, String firstName, String lastName, String email) {
	        this.username = username;
	        this.password = password;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.userRole = UserRole.EMPLOYEE;
	    }

	    public Users() {
	    }


	    public long getId() {
	        return user_id;
	    }

	    public void setId(long id) {
	        this.user_id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getUserRole() {
	        if (userRole == UserRole.MANAGER) {
	            return "MANAGER";
	        } else {
	            return "EMPLOYEE";
	        }
	    }

	    public UserRole setUserRole(String userRole) {
	        if (userRole.equalsIgnoreCase("employee")) {
	            return this.userRole = UserRole.EMPLOYEE;
	        }
	        if (userRole.equalsIgnoreCase("MANAGER")) {
	            this.userRole = UserRole.MANAGER;
	        }
	        return null;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    @Override
	    public String toString() {
	        return "Users{" +
	                "user_id=" + user_id +
	                ", username='" + username + '\'' +
	                ", password='" + password + '\'' +
	                ", userRole=" + userRole +
	                '}';
	    }
	}
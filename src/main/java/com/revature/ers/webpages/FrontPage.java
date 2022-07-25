package com.revature.ers.webpages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.ers.controller.LoginController;
import com.revature.ers.controller.ReimbursementController;
import com.revature.ers.controller.SessionController;
import com.revature.ers.controller.UserController;
import com.revature.ers.exceptions.InvalidCredentialsException;






/**
 * Servlet impclementation class FrontPage
 */
public class FrontPage {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, InvalidCredentialsException {
		System.out.println("In the FrontPage Servlet with URI: " + req.getRequestURI());
		switch(req.getRequestURI()) {
			case "/api/login":
				LoginController.login(req, res);
				break;
			case "/api/signup":
				LoginController.signUp(req, res);
				break;
			case "/api/updateuser":
				UserController.updateUser(req, res);
				break;
			case "/api/finduser":
				UserController.findUser(req, res);
				break;
			case "/api/session":
				SessionController.getSession(req, res);
		}
	}
	
}
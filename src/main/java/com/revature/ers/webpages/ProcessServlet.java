package com.revature.ers.webpages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

import com.revature.ers.exceptions.InvalidCredentialsException;

/**
 * Servlet implementation class ProcessServlet
 */
@WebServlet(name = "ProcessServlet", urlPatterns = "/api/*")
public class ProcessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ProcessServlet Was called > Get Method");
		try {
			FrontPage.process(req, res);
		} catch (InvalidCredentialsException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ProcessServlet Was called > POST Method");
		try {
			FrontPage.process(req, res);
		} catch (InvalidCredentialsException e) {
			e.printStackTrace();
		}
	}

}

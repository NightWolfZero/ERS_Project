package com.revature.ers.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Users;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.exceptions.InvalidCredentialsException;

/**
 * Servlet implementation class ReimbursementController
 */
public class ReimbursementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	   private static ReimbursementService rServ = new ReimbursementService();

	    public static void findAllReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, InvalidCredentialsException {
	        List<Reimbursement> reimbursements = rServ.getAllReimbursements();
	        res.getWriter().write(new ObjectMapper().writeValueAsString(reimbursements));

	    }

	    public static void getReimbursementByUserid(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, InvalidCredentialsException {
	        //To read stringified JSON data
	        StringBuilder buffer = new StringBuilder();
	        BufferedReader reader = req.getReader();

	        String line;
	        while((line = reader.readLine()) != null) {
	            buffer.append(line);
	            buffer.append(System.lineSeparator());
	        }

	        String data = buffer.toString();
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode parsedObj = mapper.readTree(data);

	        int author_id = Integer.parseInt(parsedObj.get("author_id").asText());

	        List<Reimbursement> reimbursements = rServ.getReimbursementByUserid(author_id);

	        res.getWriter().write(new ObjectMapper().writeValueAsString(reimbursements));

	    }

	    public static void updateReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, InvalidCredentialsException {
	        System.out.println("In Update Reimbursement Method!!");
	        //To read in stringified JSON data from a POST request is a little more complicated than reading form data
	        StringBuilder buffer = new StringBuilder();

	        //The buffered reader will read the json data line by line
	        BufferedReader reader = req.getReader();

	        String line;
	        while((line = reader.readLine()) != null) {
	            buffer.append(line);
	            buffer.append(System.lineSeparator());
	        }

	        String data = buffer.toString();
	        System.out.println(data);

	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode parsedObj = mapper.readTree(data);

	        Long request_id = parsedObj.get("request_id").asLong();
	        Long amount = parsedObj.get("amount").asLong();
	        String  submitted = parsedObj.get("submitted").asText();
	        String  resolved = parsedObj.get("resolved").asText();
	        String description = parsedObj.get("description").asText();
	        Long author_id = parsedObj.get("author_id").asLong();
	        Long resolver_id = parsedObj.get("resolver_id").asLong();
	        String reimbursementType = parsedObj.get("reimbursementType").asText();
	        String reimbursementStatus = parsedObj.get("reimbursementStatus").asText();
	        Reimbursement reimbursement = new Reimbursement(amount,submitted,resolved,description,author_id,resolver_id);
	        reimbursement.setId(request_id);
	        reimbursement.setResolver_id(resolver_id);
	        reimbursement.setReimbursementType(reimbursementType);
	        reimbursement.setReimbursementStatus(reimbursementStatus);

	        try {
	            System.out.println("In the Reimbursement Update handler");
	            Reimbursement u = rServ.updateReimbursementStatus(reimbursement);
	            System.out.println(u);
	            res.setStatus(200);
	            res.getWriter().write(new ObjectMapper().writeValueAsString(u));
	        } catch(Exception e) {
	            res.setStatus(403);
	            res.getWriter().println("Couldn't Update Reimbursement!!");
	        }

	    }

	    public static void saveReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {

	        //To read in stringified JSON data from a POST request is a little more complicated than reading form data
	        StringBuilder buffer = new StringBuilder();

	        //The buffered reader will read the json data line by line
	        BufferedReader reader = req.getReader();

	        String line;
	        while((line = reader.readLine()) != null) {
	            buffer.append(line);
	            buffer.append(System.lineSeparator());
	        }

	        String data = buffer.toString();
	        System.out.println(data);

	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode parsedObj = mapper.readTree(data);

	        Long amount = parsedObj.get("amount").asLong();
	        String description = parsedObj.get("description").asText();
	        Long author_id = parsedObj.get("author_id").asLong();
	        String reimbursementType = parsedObj.get("reimbursementType").asText();
	        Reimbursement reimbursement = new Reimbursement(amount,description,author_id);
	        reimbursement.setReimbursementType(reimbursementType);

	        try {
	            System.out.println("In the reimbursement Creator handler");
	            Reimbursement u = rServ.saveReimbursement(reimbursement);
	            System.out.println(u);
	            res.setStatus(200);
	            res.getWriter().write(new ObjectMapper().writeValueAsString(u));
	        } catch(Exception e) {
	            res.setStatus(403);
	            res.getWriter().println("Could not Create Reimbursement");
	        }

	    }

	    public static void findReimbursementById(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, InvalidCredentialsException {
	        System.out.println("In Find Reimbursement BY ID Method!!");
	        //To read in stringified JSON data from a POST request is a little more complicated than reading form data
	        StringBuilder buffer = new StringBuilder();

	        //The buffered reader will read the json data line by line
	        BufferedReader reader = req.getReader();

	        String line;
	        while((line = reader.readLine()) != null) {
	            buffer.append(line);
	            buffer.append(System.lineSeparator());
	        }

	        String data = buffer.toString();
	        System.out.println(data);

	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode parsedObj = mapper.readTree(data);

	        int request_id = parsedObj.get("request_id").asInt();


	        try {
	            System.out.println("In the Find Reimbursement BY ID handler");
	            Reimbursement u = rServ.getReimbursement(request_id);
	            System.out.println(u);
	            res.setStatus(200);
	            res.getWriter().write(new ObjectMapper().writeValueAsString(u));
	        } catch(Exception e) {
	            res.setStatus(403);
	            res.getWriter().println("Couldn't Find Reimbursement!!");
	        }

	    }

	}

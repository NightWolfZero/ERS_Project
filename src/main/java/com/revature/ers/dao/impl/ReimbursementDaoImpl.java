package com.revature.ers.dao.impl;

import com.revature.ers.utils.ConnectionUtils;
import com.revature.ers.dao.ReimbursementDao;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Users;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

	public class ReimbursementDaoImpl implements ReimbursementDao {

	    @Override
	    public List<Reimbursement> getAllReimbursements() {
	        List<Reimbursement> reimbursements = new ArrayList<>();
	        Connection connection = null;
	        Statement stmt = null;

	        try {
	            connection = ConnectionUtils.getInstance().getConnection();

	            stmt = connection.createStatement();

	            String sql = "SELECT * FROM reimbursement";

	            ResultSet rs = stmt.executeQuery(sql);

	            while (rs.next()) {
	                Reimbursement a = new Reimbursement();
	                a.setId(rs.getLong("request_id"));
	                a.setAmount(rs.getLong("amount"));
	                a.setSubmitted(rs.getString("submitted"));
	                a.setResolved(rs.getString("res"
	                		+ "olved"));
	                a.setDescription(rs.getString("description"));
	                a.setAuthor_id(rs.getLong("author_id"));
	                a.setResolver_id(rs.getLong("resolver_id"));
	                a.setReimbursementType(rs.getString("reimbursementType"));
	                a.setReimbursementStatus(rs.getString("reimbursementStatus"));

	                reimbursements.add(a);
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

	        return reimbursements;
	    }

	    @Override
	    public Reimbursement getReimbursement(int requestid) {
	        Reimbursement reimbursement = new Reimbursement();
	        Connection connection = null;
	        PreparedStatement stmt = null;

	        try {
	            connection = ConnectionUtils.getInstance().getConnection();

	            String sql = "SELECT * FROM Reimbursement WHERE request_id = ?";
	            stmt = connection.prepareStatement(sql);
	            stmt.setLong(1, requestid);


	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()){
	                reimbursement.setId(rs.getLong("request_id"));
	                reimbursement.setAmount(rs.getLong("amount"));
	                reimbursement.setSubmitted(rs.getString("submitted"));
	                reimbursement.setResolved(rs.getString("resolved"));
	                reimbursement.setDescription(rs.getString("description"));
	                reimbursement.setAuthor_id(rs.getLong("author_id"));
	                reimbursement.setResolver_id(rs.getLong("resolver_id"));
	                reimbursement.setReimbursementType(rs.getString("reimbursementType"));
	                reimbursement.setReimbursementStatus(rs.getString("reimbursementStatus"));
	            }else {
	                reimbursement.setId(0);
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
	        return reimbursement;
	    }

	    @Override
	    public List<Reimbursement> getReimbursementByUserid(int author_id) {
	        List<Reimbursement>  reimbursement = new ArrayList<>();
	        Connection connection = null;
	        PreparedStatement stmt = null;

	        try {
	            connection = ConnectionUtils.getInstance().getConnection();

	            String sql = "SELECT * FROM Reimbursement WHERE author_id = ?";
	            stmt = connection.prepareStatement(sql);
	            stmt.setLong(1, author_id);


	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()){
	                Reimbursement a = new Reimbursement();
	                a.setId(rs.getLong("request_id"));
	                a.setAmount(rs.getLong("amount"));
	                a.setSubmitted(rs.getString("submitted"));
	                a.setResolved(rs.getString("resolved"));
	                a.setDescription(rs.getString("description"));
	                a.setAuthor_id(rs.getLong("author_id"));
	                a.setResolver_id(rs.getLong("resolver_id"));
	                a.setReimbursementType(rs.getString("reimbursementType"));
	                a.setReimbursementStatus(rs.getString("reimbursementStatus"));
	                reimbursement.add(a);
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
	        return reimbursement;
	    }

	    @Override
	    public Reimbursement saveReimbursement(Reimbursement reimbursementToSave) throws Exception {
	        Connection connection = null;
	        PreparedStatement stmt = null;
	        int success = 0;

	        try {
	            connection = ConnectionUtils.getInstance().getConnection();
	            String sql = "INSERT INTO Reimbursement VALUES (?,?,?,?,?,?,?,?,?)";

	            // Setup PreparedStatement
	            stmt = connection.prepareStatement(sql);

	            // Add parameters from user into PreparedStatement
	            stmt.setLong(1, reimbursementToSave.getId());
	            stmt.setLong(2, reimbursementToSave.getAmount());
	            stmt.setString(3, reimbursementToSave.getSubmitted());
	            stmt.setString(4, reimbursementToSave.getResolved());
	            stmt.setString(5, reimbursementToSave.getDescription());
	            stmt.setLong(6, reimbursementToSave.getAuthor_id());
	            stmt.setLong(7, reimbursementToSave.getResolver_id());
	            stmt.setString(8, reimbursementToSave.getReimbursementType());
	            stmt.setString(9, reimbursementToSave.getReimbursementStatus());



	            success = stmt.executeUpdate();
	            return reimbursementToSave;
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
	            throw new Exception("Insert Reimbursement failed: " + reimbursementToSave);
	        }

	        return reimbursementToSave;
	    }


	    @Override
	    public void updateReimbursementStatus(Reimbursement reimbursementStatusToUpdate) throws Exception {

	        Connection connection = null;
	        PreparedStatement stmt = null;
	        int success = 0;

	        try {
	            connection = ConnectionUtils.getInstance().getConnection();
	            String sql = "update Reimbursement set reimbursementStatus = ?,resolved = ? ,resolver_id = ? where request_id = ?";


	            // Setup PreparedStatement
	            stmt = connection.prepareStatement(sql);

	            // Add parameters from user into PreparedStatement
	            stmt.setString(1, reimbursementStatusToUpdate.getReimbursementStatus());
	            stmt.setString(2, reimbursementStatusToUpdate.getResolved());
	            stmt.setLong(3, reimbursementStatusToUpdate.getResolver_id());
	            stmt.setLong(4, reimbursementStatusToUpdate.getId());


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
	            throw new Exception("Failed to Update Reimbursement Status > : " + reimbursementStatusToUpdate);
	        }
	    }
	}
}
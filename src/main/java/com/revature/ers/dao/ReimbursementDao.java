package com.revature.ers.dao;

import java.util.List;

import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Users;

public interface ReimbursementDao {
	List<Reimbursement> getAllReimbursements();

    Reimbursement getReimbursement(int requestid);

    List<Reimbursement> getReimbursementByUserid(int author_id);

    Reimbursement saveReimbursement(Reimbursement reimbursementToSave) throws Exception;

    void updateReimbursementStatus(Reimbursement reimbursementStatusToUpdate) throws Exception;


}


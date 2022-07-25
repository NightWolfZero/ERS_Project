package com.revature.ers.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.revature.ers.dao.ReimbursementDao;
import com.revature.ers.log.LogThis;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.utils.ConnectionUtils;
import com.revature.ers.models.Users;
import com.revature.ers.dao.UserDao;
import com.revature.ers.exceptions.InvalidCredentialsException;

public class ReimbursementService {
    //Call DAO method
    ReimbursementDao dao = ConnectionUtils.getReimbursementDao();

    public List<Reimbursement> getAllReimbursements() {
        return dao.getAllReimbursements();
    }

    public Reimbursement getReimbursement(int requestid) {
        Reimbursement reimbursement = dao.getReimbursement(requestid);
        return reimbursement;
    }

    public List<Reimbursement> getReimbursementByUserid(int author_id) {
        return dao.getReimbursementByUserid(author_id);
    }



    public Reimbursement updateReimbursementStatus(Reimbursement reimbursementStatusToUpdate) throws Exception {
        dao.updateReimbursementStatus(reimbursementStatusToUpdate);
        LogThis.logger.warn("Reimbursement: " + reimbursementStatusToUpdate.getId() + " 's Status has been changed to " + reimbursementStatusToUpdate.getReimbursementStatus());
        return reimbursementStatusToUpdate;
    }


    public Reimbursement saveReimbursement(Reimbursement reimbursementToSave) {
        try {
            dao.saveReimbursement(reimbursementToSave);
            LogThis.logger.info("Reimbursement: " + reimbursementToSave.getReimbursementType() + " was Created");
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            System.out.println("ID is already in use");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem creating the Reimbursement at this time");
        }
        return reimbursementToSave;
    }


}

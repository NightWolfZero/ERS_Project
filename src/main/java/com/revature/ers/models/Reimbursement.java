package com.revature.ers.models;

import java.util.Date;
import java.time.LocalDate;

public class Reimbursement {
	private long request_id;
    private long amount;
    private String submitted;
    private String resolved;
    private String description;
    private long author_id;
    private long resolver_id;
    private ReimbursementType reimbursementType;
    private ReimbursementStatus reimbursementStatus;

    public Reimbursement(long amount, String submitted, String resolved, String description, long author_id, long resolver_id) {
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author_id = author_id;
        this.resolver_id = resolver_id;
    }

    public Reimbursement() {
    }

    public Reimbursement(long amount, String description, long author_id) {
        this.amount = amount;
        this.description = description;
        this.author_id = author_id;
    }

    public long getId() {
        return request_id;
    }

    public void setId(long request_id) {
        this.request_id = request_id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        if (submitted == null) {
            submitted = LocalDate.now().toString();
        }
        return submitted;
    }

    public void setSubmitted(String submitted) {

        this.submitted = submitted;


    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {

        this.resolved = resolved;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public long getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(long resolver_id) {
        this.resolver_id = resolver_id;
    }

    public String getReimbursementType() {
        if (reimbursementType == ReimbursementType.FOOD) {
            return "FOOD";
        }
        if (reimbursementType == ReimbursementType.TRAVEL) {
            return "TRAVEL";
        }
        if (reimbursementType == ReimbursementType.LODGING) {
            return "LODGING";
        }
        return "OTHER";
    }

    public ReimbursementType setReimbursementType(String reimbursementType) {
        if (reimbursementType.equalsIgnoreCase("FOOD")) {
            return this.reimbursementType = ReimbursementType.FOOD;
        }
        if (reimbursementType.equalsIgnoreCase("TRAVEL")) {
            return this.reimbursementType = ReimbursementType.TRAVEL;
        }
        if (reimbursementType.equalsIgnoreCase("LODGING")) {
            return this.reimbursementType = ReimbursementType.LODGING;
        }
        if (reimbursementType.equalsIgnoreCase("OTHER")) {
            return this.reimbursementType = ReimbursementType.OTHER;
        }
        return null;
    }

    public String getReimbursementStatus() {
        if (reimbursementStatus == ReimbursementStatus.APPROVED) {
            return "APPROVED";
        }
        if (reimbursementStatus == ReimbursementStatus.DENIED) {
            return "DENIED";
        } else {
            return "PENDING";
        }
    }

    public ReimbursementStatus setReimbursementStatus(String reimbursementStatus) {
        if (reimbursementStatus.equalsIgnoreCase("APPROVED")) {
            return this.reimbursementStatus = ReimbursementStatus.APPROVED;
        }
        if (reimbursementStatus.equalsIgnoreCase("PENDING")) {
            return this.reimbursementStatus = ReimbursementStatus.PENDING;
        }
        if (reimbursementStatus.equalsIgnoreCase("DENIED")) {
            return this.reimbursementStatus = ReimbursementStatus.DENIED;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "request_id=" + request_id +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", author_id=" + author_id +
                ", resolver_id=" + resolver_id +
                ", reimbursementType=" + reimbursementType +
                ", reimbursementStatus=" + reimbursementStatus +
                '}';
    }
}
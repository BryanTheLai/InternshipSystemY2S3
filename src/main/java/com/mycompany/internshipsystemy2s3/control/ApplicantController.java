package com.mycompany.internshipsystemy2s3.control;

import com.mycompany.internshipsystemy2s3.dao.ApplicantDAO;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.dao.ApplicantDAO.FilterCriteria;

public class ApplicantController {
    private ApplicantDAO applicantDAO;

    public ApplicantController() {
        this.applicantDAO = new ApplicantDAO();
    }

    // Generate a new ID for applicants
    public String generateNewId() {
        return applicantDAO.generateId();
    }

    // Add a new applicant
    public void addApplicant(Applicant applicant) {
        applicantDAO.addApplicant(applicant);
    }

    // Update an existing applicant
    public void updateApplicant(String id, Applicant updatedApplicant) {
        applicantDAO.updateApplicant(id, updatedApplicant);
    }

    // Remove an applicant
    public void removeApplicant(String id) {
        applicantDAO.removeApplicant(id);
    }

    // Filter applicants based on criteria
    public ListInterface<Applicant> filterApplicants(FilterCriteria criteria) {
        return applicantDAO.filterApplicants(criteria);
    }

    // Get all applicants
    public ListInterface<Applicant> getAllApplicants() {
        return applicantDAO.getAllApplicants();
    }
}
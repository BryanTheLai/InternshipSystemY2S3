package com.mycompany.internshipsystemy2s3.control;

import com.mycompany.internshipsystemy2s3.dao.ApplicantDAO;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;

public class ApplicantManagement {
    private ApplicantDAO applicantDAO;

    public ApplicantManagement() {
        this.applicantDAO = new ApplicantDAO();
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
    public ListInterface<Applicant> filterApplicants(ApplicantDAO.FilterCriteria criteria) {
        return applicantDAO.filterApplicants(criteria);
    }

    // Get all applicants
    public ListInterface<Applicant> getAllApplicants() {
        return applicantDAO.getAllApplicants();
    }
}
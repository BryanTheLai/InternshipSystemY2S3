package com.mycompany.internshipsystemy2s3.control;

import com.mycompany.internshipsystemy2s3.dao.ApplicantDAO;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedListInterface;

public class ApplicantManagement {
    private ApplicantDAO applicantDAO;

    public ApplicantManagement() {
        this.applicantDAO = new ApplicantDAO();
    }

    public void addApplicant(Applicant applicant) {
        applicantDAO.addApplicant(applicant);
    }

    public void updateApplicant(String id, Applicant updatedApplicant) {
        applicantDAO.updateApplicant(id, updatedApplicant);
    }

    public void removeApplicant(String id) {
        applicantDAO.removeApplicant(id);
    }

    public DoublyLinkedListInterface<Applicant> filterApplicants(ApplicantDAO.FilterCriteria criteria) {
        return applicantDAO.filterApplicants(criteria);
    }

    public DoublyLinkedListInterface<Applicant> getAllApplicants() {
        return applicantDAO.getAllApplicants();
    }
}
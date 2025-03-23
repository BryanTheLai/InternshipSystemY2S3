package com.mycompany.internshipsystemy2s3.dao;

import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.entity.Applicant;

/**
 * Data Access Object for Applicant entities
 * Handles CRUD operations for applicants
 */
public class ApplicantDAO implements ApplicantDAOInterface {
    private ListInterface<Applicant> applicants;
    private int nextId;

    // For backwards compatibility - redirecting to the initializer
    public static final String[] MALAYSIAN_UNIVERSITIES = ApplicantInitializer.MALAYSIAN_UNIVERSITIES;
    public static final String[] MAJORS = ApplicantInitializer.MAJORS;

    /**
     * Constructor - loads sample data from initializer
     */
    public ApplicantDAO() {
        // Use the initializer to load sample data
        ApplicantInitializer initializer = new ApplicantInitializer();
        this.applicants = initializer.initializeApplicants();
        this.nextId = 13; // Based on sample data (12 initial records)
    }

    /**
     * Generates a unique ID for new applicants
     * 
     * @return String containing new ID
     */
    @Override
    public String generateId() {
        return String.format("ID%03d", nextId++);
    }

    /**
     * Adds a new applicant
     * 
     * @param applicant The applicant to add
     */
    @Override
    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }

    /**
     * Updates an existing applicant
     * 
     * @param id               ID of the applicant to update
     * @param updatedApplicant New applicant data
     */
    @Override
    public void updateApplicant(String id, Applicant updatedApplicant) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.add(i, updatedApplicant);
                applicants.remove(i + 1);
                return;
            }
        }
    }

    /**
     * Removes an applicant
     * 
     * @param id ID of the applicant to remove
     */
    @Override
    public void removeApplicant(String id) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.remove(i);
                return;
            }
        }
    }

    /**
     * Filters applicants based on provided criteria
     * 
     * @param criteria The filtering criteria to apply
     * @return List of applicants matching criteria
     */
    @Override
    public ListInterface<Applicant> filterApplicants(FilterCriteria criteria) {
        ListInterface<Applicant> filteredApplicants = new DoublyLinkedList<>();
        for (int i = 0; i < applicants.size(); i++) {
            Applicant applicant = applicants.get(i);
            if (criteria.matches(applicant)) {
                filteredApplicants.add(applicant);
            }
        }
        return filteredApplicants;
    }

    /**
     * Gets all applicants
     * 
     * @return List of all applicants
     */
    @Override
    public ListInterface<Applicant> getAllApplicants() {
        return applicants;
    }
}
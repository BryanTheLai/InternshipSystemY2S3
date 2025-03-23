package com.mycompany.internshipsystemy2s3.control;

import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.dao.ApplicantDAOInterface.FilterCriteria;

/**
 * Interface defining controller operations for the Applicant Management System
 */
public interface ApplicantControllerInterface {
    
    /**
     * Run the application, initializing UI and displaying main menu
     */
    void runApplicantManagement();
    
    /**
     * Generate a new unique ID for applicants
     * @return A formatted ID string
     */
    String generateNewId();
    
    /**
     * Add a new applicant to the system
     * @param applicant The applicant to add
     */
    void addApplicant(Applicant applicant);
    
    /**
     * Update an existing applicant
     * @param id The ID of the applicant to update
     * @param updatedApplicant The updated applicant data
     * @return true if update was successful, false if applicant not found
     */
    boolean updateApplicant(String id, Applicant updatedApplicant);
    
    /**
     * Remove an applicant from the system
     * @param id The ID of the applicant to remove
     * @return true if removal was successful, false if applicant not found
     */
    boolean removeApplicant(String id);
    
    /**
     * Filter applicants based on provided criteria
     * @param criteria The filtering criteria to apply
     * @return List of applicants matching the criteria
     */
    ListInterface<Applicant> filterApplicants(FilterCriteria criteria);
    
    /**
     * Get all applicants in the system
     * @return List of all applicants
     */
    ListInterface<Applicant> getAllApplicants();
    
    /**
     * Find an applicant by their ID
     * @param id The ID to search for
     * @return The applicant if found, null otherwise
     */
    Applicant findApplicantById(String id);
}
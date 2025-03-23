package com.mycompany.internshipsystemy2s3.dao;

import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.entity.Applicant;

/**
 * Interface defining data access operations for Applicant entities
 */
public interface ApplicantDAOInterface {
    
    /**
     * Generates a unique ID for new applicants
     * @return String containing new ID
     */
    String generateId();
    
    /**
     * Adds a new applicant
     * @param applicant The applicant to add
     */
    void addApplicant(Applicant applicant);
    
    /**
     * Updates an existing applicant
     * @param id ID of the applicant to update
     * @param updatedApplicant New applicant data
     */
    void updateApplicant(String id, Applicant updatedApplicant);
    
    /**
     * Removes an applicant
     * @param id ID of the applicant to remove
     */
    void removeApplicant(String id);
    
    /**
     * Filters applicants based on provided criteria
     * @param criteria The filtering criteria to apply
     * @return List of applicants matching criteria
     */
    ListInterface<Applicant> filterApplicants(FilterCriteria criteria);
    
    /**
     * Gets all applicants
     * @return List of all applicants
     */
    ListInterface<Applicant> getAllApplicants();
    
    /**
     * Interface for defining applicant filtering criteria
     */
    interface FilterCriteria {
        boolean matches(Applicant applicant);
    }
}
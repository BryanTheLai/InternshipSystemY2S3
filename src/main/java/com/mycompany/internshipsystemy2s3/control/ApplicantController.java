package com.mycompany.internshipsystemy2s3.control;

import com.mycompany.internshipsystemy2s3.dao.ApplicantDAO;
import com.mycompany.internshipsystemy2s3.dao.ApplicantDAOInterface;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.dao.ApplicantDAOInterface.FilterCriteria;
import com.mycompany.internshipsystemy2s3.boundary.ApplicantManagementUI;

/**
 * Controller class for managing applicant-related operations
 * Acts as the coordinator between the UI and DAO layers
 */
public class ApplicantController implements ApplicantControllerInterface {
    private ApplicantDAOInterface applicantDAO;
    private ApplicantManagementUI applicantUI;

    /**
     * Default constructor - initializes the DAO
     */
    public ApplicantController() {
        this.applicantDAO = new ApplicantDAO();
    }
    
    /**
     * Constructor allowing dependency injection of DAO for testing
     * @param dao The DAO implementation to use
     */
    public ApplicantController(ApplicantDAOInterface dao) {
        this.applicantDAO = dao;
    }

    /**
     * Runs the application by initializing the UI and displaying the main menu
     */
    @Override
    public void runApplicantManagement() {
        // Create UI and pass this controller - prevents circular dependency
        this.applicantUI = new ApplicantManagementUI(this);
        applicantUI.displayMainMenu();
    }

    /**
     * Generate a new unique ID for applicants
     * 
     * @return A formatted ID string
     */
    @Override
    public String generateNewId() {
        return applicantDAO.generateId();
    }

    /**
     * Add a new applicant to the system
     * 
     * @param applicant The applicant to add
     */
    @Override
    public void addApplicant(Applicant applicant) {
        applicantDAO.addApplicant(applicant);
    }

    /**
     * Update an existing applicant
     * 
     * @param id               The ID of the applicant to update
     * @param updatedApplicant The updated applicant data
     * @return true if update was successful, false if applicant not found
     */
    @Override
    public boolean updateApplicant(String id, Applicant updatedApplicant) {
        try {
            applicantDAO.updateApplicant(id, updatedApplicant);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Remove an applicant from the system
     * 
     * @param id The ID of the applicant to remove
     * @return true if removal was successful, false if applicant not found
     */
    @Override
    public boolean removeApplicant(String id) {
        try {
            applicantDAO.removeApplicant(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Filter applicants based on provided criteria
     * 
     * @param criteria The filtering criteria to apply
     * @return List of applicants matching the criteria
     */
    @Override
    public ListInterface<Applicant> filterApplicants(FilterCriteria criteria) {
        return applicantDAO.filterApplicants(criteria);
    }

    /**
     * Get all applicants in the system
     * 
     * @return List of all applicants
     */
    @Override
    public ListInterface<Applicant> getAllApplicants() {
        return applicantDAO.getAllApplicants();
    }
    
    /**
     * Find an applicant by their ID
     * 
     * @param id The ID to search for
     * @return The applicant if found, null otherwise
     */
    @Override
    public Applicant findApplicantById(String id) {
        ListInterface<Applicant> allApplicants = applicantDAO.getAllApplicants();
        for (int i = 0; i < allApplicants.size(); i++) {
            Applicant app = allApplicants.get(i);
            if (app.getId().equals(id)) {
                return app;
            }
        }
        return null;
    }

    /**
     * Application entry point - creates and runs the controller
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Starting Internship Applicant Management System...");
        ApplicantControllerInterface controller = new ApplicantController();
        controller.runApplicantManagement();
    }
}
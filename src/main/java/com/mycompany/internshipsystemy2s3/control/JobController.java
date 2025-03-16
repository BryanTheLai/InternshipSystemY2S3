package com.mycompany.internshipsystemy2s3.control;

import com.mycompany.internshipsystemy2s3.dao.JobDAO;
import com.mycompany.internshipsystemy2s3.dao.JobDAOInterface;
import com.mycompany.internshipsystemy2s3.entity.Job;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.dao.JobDAOInterface.FilterCriteria;
import com.mycompany.internshipsystemy2s3.boundary.JobManagementUI;


/**
 * Controller class for managing job-related operations
 * Acts as the coordinator between the UI and DAO layers for Job management
 */
public class JobController implements JobControllerInterface {
    private JobDAOInterface jobDAO;
    private JobManagementUI jobUI;

    /**
     * Default constructor - initializes the DAO
     */
    public JobController() {
        this.jobDAO = new JobDAO();
    }

    /**
     * Constructor allowing dependency injection of DAO for testing
     * @param dao The DAO implementation to use
     */
    public JobController(JobDAOInterface dao) {
        this.jobDAO = dao;
    }

    /**
     * Runs the job management application by initializing the UI and displaying the main menu
     */
    @Override
    public void runJobManagement() {
        this.jobUI = new JobManagementUI(this);
        jobUI.displayMainMenu();
    }

    /**
     * Generate a new unique ID for jobs
     * @return String containing new Job ID
     */
    @Override
    public String generateNewId() {
        return jobDAO.generateId();
    }

    /**
     * Add a new job posting
     * @param job The job to add
     */
    @Override
    public void addJob(Job job) {
        jobDAO.addJob(job);
    }

    /**
     * Updates an existing job posting
     * @param jobId ID of the job to update
     * @param updatedJob The updated job data
     * @return true if update was successful, false if job not found
     */
    @Override
    public boolean updateJob(String jobId, Job updatedJob) {
        try {
            jobDAO.updateJob(jobId, updatedJob);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Removes a job posting
     * @param jobId ID of the job to remove
     * @return true if removal was successful, false if job not found
     */
    @Override
    public boolean removeJob(String jobId) {
        try {
            jobDAO.removeJob(jobId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Filters jobs based on provided criteria
     * @param criteria The filtering criteria to apply
     * @return List of jobs matching the criteria
     */
    @Override
    public ListInterface<Job> filterJobs(FilterCriteria criteria) {
        return jobDAO.filterJobs(criteria);
    }

    /**
     * Gets all job postings
     * @return List of all jobs
     */
    @Override
    public ListInterface<Job> getAllJobs() {
        return jobDAO.getAllJobs();
    }

    /**
     * Finds a job by its ID
     * @param jobId The ID to search for
     * @return The Job if found, null otherwise
     */
    @Override
    public Job findJobById(String jobId) {
        return jobDAO.findJobById(jobId);
    }

    /**
     * Application entry point for Job Management Module
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Starting Internship Job Management System...");
        JobControllerInterface controller = new JobController();
        controller.runJobManagement();
    }
}
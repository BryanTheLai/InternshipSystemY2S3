package com.mycompany.internshipsystemy2s3.control;

import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.entity.Job;
import com.mycompany.internshipsystemy2s3.dao.JobDAOInterface.FilterCriteria;

/**
 * Interface defining controller operations for the Job Management System
 */
public interface JobControllerInterface {

    /**
     * Run the job management application, initializing UI and displaying main menu
     */
    void runJobManagement();

    /**
     * Generate a new unique ID for jobs
     * @return String containing new Job ID
     */
    String generateNewId();

    /**
     * Add a new job posting
     * @param job The job to add
     */
    void addJob(Job job);

    /**
     * Updates an existing job posting
     * @param jobId ID of the job to update
     * @param updatedJob The updated job data
     * @return true if update was successful, false if job not found
     */
    boolean updateJob(String jobId, Job updatedJob);

    /**
     * Removes a job posting
     * @param jobId ID of the job to remove
     * @return true if removal was successful, false if job not found
     */
    boolean removeJob(String jobId);

    /**
     * Filters jobs based on provided criteria
     * @param criteria The filtering criteria to apply
     * @return List of jobs matching the criteria
     */
    ListInterface<Job> filterJobs(FilterCriteria criteria);

    /**
     * Gets all job postings
     * @return List of all jobs
     */
    ListInterface<Job> getAllJobs();

    /**
     * Finds a job by its ID
     * @param jobId The ID to search for
     * @return The Job if found, null otherwise
     */
    Job findJobById(String jobId);
}
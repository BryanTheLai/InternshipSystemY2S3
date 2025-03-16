package com.mycompany.internshipsystemy2s3.dao;

import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.entity.Job;

/**
 * Interface defining data access operations for Job entities
 */
public interface JobDAOInterface {

    /**
     * Generates a unique ID for new jobs
     * @return String containing new Job ID
     */
    String generateId();

    /**
     * Adds a new job posting
     * @param job The job to add
     */
    void addJob(Job job);

    /**
     * Updates an existing job posting
     * @param jobId ID of the job to update
     * @param updatedJob New job data
     */
    void updateJob(String jobId, Job updatedJob);

    /**
     * Removes a job posting
     * @param jobId ID of the job to remove
     */
    void removeJob(String jobId);

    /**
     * Filters jobs based on provided criteria
     * @param criteria The filtering criteria to apply
     * @return List of jobs matching criteria
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


    /**
     * Interface for defining job filtering criteria
     */
    interface FilterCriteria {
        boolean matches(Job job);
    }
}
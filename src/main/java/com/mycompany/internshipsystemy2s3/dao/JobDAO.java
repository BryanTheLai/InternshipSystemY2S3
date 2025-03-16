package com.mycompany.internshipsystemy2s3.dao;

import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.entity.Job;

/**
 * Data Access Object for Job entities
 * Handles CRUD operations for jobs
 */
public class JobDAO implements JobDAOInterface {
    private ListInterface<Job> jobs;
    private int nextJobId;

    // For backwards compatibility - redirecting to the initializer
    public static final String[] LOCATIONS = JobInitializer.LOCATIONS;
    public static final String[] SKILLS = JobInitializer.SKILLS;


    /**
     * Constructor - loads sample data from initializer
     */
    public JobDAO() {
        JobInitializer initializer = new JobInitializer();
        this.jobs = initializer.initializeJobs();
        this.nextJobId = 6; // Based on sample data (5 initial records)
    }

    /**
     * Generates a unique ID for new jobs
     * @return String containing new Job ID
     */
    @Override
    public String generateId() {
        return String.format("J%03d", nextJobId++);
    }

    /**
     * Adds a new job posting
     * @param job The job to add
     */
    @Override
    public void addJob(Job job) {
        jobs.add(job);
    }

    /**
     * Updates an existing job posting
     * @param jobId ID of the job to update
     * @param updatedJob New job data
     */
    @Override
    public void updateJob(String jobId, Job updatedJob) {
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getJobId().equals(jobId)) {
                jobs.add(i, updatedJob);
                jobs.remove(i + 1);
                return;
            }
        }
    }

    /**
     * Removes a job posting
     * @param jobId ID of the job to remove
     */
    @Override
    public void removeJob(String jobId) {
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getJobId().equals(jobId)) {
                jobs.remove(i);
                return;
            }
        }
    }

    /**
     * Filters jobs based on provided criteria
     * @param criteria The filtering criteria to apply
     * @return List of jobs matching criteria
     */
    @Override
    public ListInterface<Job> filterJobs(FilterCriteria criteria) {
        ListInterface<Job> filteredJobs = new DoublyLinkedList<>();
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            if (criteria.matches(job)) {
                filteredJobs.add(job);
            }
        }
        return filteredJobs;
    }

    /**
     * Gets all job postings
     * @return List of all jobs
     */
    @Override
    public ListInterface<Job> getAllJobs() {
        return jobs;
    }

    /**
     * Finds a job by its ID
     * @param jobId The ID to search for
     * @return The Job if found, null otherwise
     */
    @Override
    public Job findJobById(String jobId) {
        ListInterface<Job> allJobs = getAllJobs();
        for (int i = 0; i < allJobs.size(); i++) {
            Job job = allJobs.get(i);
            if (job.getJobId().equals(jobId)) {
                return job;
            }
        }
        return null;
    }

    private void prefillData() {
        // Add sample job postings (without jobType, using salaryAmount)
        addSampleJob("J001", "Software Engineer Intern", "Exciting internship for aspiring software engineers.",
                "TechCorp Inc.", "Cyberjaya", 2500.00, "Java, Python, Git", "2024-07-01");
        addSampleJob("J002", "Frontend Developer Intern", "Join our team to build amazing user interfaces.",
                "WebSolutions Ltd.", "Petaling Jaya", 5000.00, "React, JavaScript, HTML, CSS", "2024-06-15");
        addSampleJob("J003", "Data Scientist Intern", "Work on cutting-edge data analysis projects.",
                "Data Insights Co.", "Kuala Lumpur", 7500.00, "Python, R, Machine Learning", "2024-06-20");
        addSampleJob("J004", "Cloud Engineer Intern", "Help us scale our infrastructure in the cloud.",
                "Cloudify Services", "Remote", 9000.00, "AWS, Azure, DevOps", "2024-06-25");
        addSampleJob("J005", "Cybersecurity Analyst Intern", "Protect our systems from cyber threats.",
                "SecureTech Solutions", "Cyberjaya", 6500.00, "Security, Networking, Linux", "2024-07-01");

        this.nextJobId = 6; // Update nextId after prefilling
    }

    private void addSampleJob(String jobId, String title, String description, String company, String location,
                              double salaryAmount, String skills, String postingDate) {
        jobs.add(new Job(jobId, title, description, company, location, salaryAmount, skills, postingDate));
    }
}
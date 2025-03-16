package com.mycompany.internshipsystemy2s3.dao;

import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.entity.Job;

/**
 * Class responsible for initializing sample job data
 */
public class JobInitializer {

    public static final String[] LOCATIONS = {
            "Shah Alam", "Cyberjaya", "Petaling Jaya", "Bangi", "Setapak",
            "Seri Kembangan", "Kajang", "Subang Jaya", "Kota Damansara",
            "Salak Tinggi", "Semenyih", "Remote", "Hybrid"
    };

    public static final String[] SKILLS = {
            "Frontend", "Backend", "Fullstack", "DevOps", "Mobile", "Data Science",
            "Machine Learning", "Cloud Engineering", "Security", "Database Administration",
            "UI/UX Design", "Web Development", "Artificial Intelligence", "Cybersecurity",
            "Project Management", "Communication", "Problem Solving", "Teamwork"
    };


    /**
     * Creates and populates a list with sample job data
     * @return ListInterface containing sample jobs
     */
    public ListInterface<Job> initializeJobs() {
        ListInterface<Job> jobs = new DoublyLinkedList<>();

        // Add sample job postings (without jobType)
        jobs.add(new Job("J001", "Software Engineer Intern", "Exciting internship for aspiring software engineers.",
                "TechCorp Inc.", "Cyberjaya", 2500.00, "Java, Python, Git", "2024-07-01"));
        jobs.add(new Job("J002", "Frontend Developer Intern", "Join our team to build amazing user interfaces.",
                "WebSolutions Ltd.", "Petaling Jaya", 5000.00, "React, JavaScript, HTML, CSS", "2024-06-15"));
        jobs.add(new Job("J003", "Data Scientist Intern", "Work on cutting-edge data analysis projects.",
                "Data Insights Co.", "Kuala Lumpur", 7500.00, "Python, R, Machine Learning", "2024-06-20"));
        jobs.add(new Job("J004", "Cloud Engineer Intern", "Help us scale our infrastructure in the cloud.",
                "Cloudify Services", "Remote", 9000.00, "AWS, Azure, DevOps", "2024-06-25"));
        jobs.add(new Job("J005", "Cybersecurity Analyst Intern", "Protect our systems from cyber threats.",
                "SecureTech Solutions", "Cyberjaya", 6500.00, "Security, Networking, Linux", "2024-07-01"));

        return jobs;
    }

    /**
     * Test method to demonstrate the initializer functionality
     */
    public static void main(String[] args) {
        JobInitializer initializer = new JobInitializer();
        ListInterface<Job> jobs = initializer.initializeJobs();

        System.out.println("\nJobs:");
        for (int i = 0; i < jobs.size(); i++) {
            System.out.println(jobs.get(i));
        }
    }
}
package com.mycompany.internshipsystemy2s3.entity;

public class Job {
    private String jobId;
    private String title;
    private String description;
    private String company;
    private String location;
    private double salaryAmount; // Changed to double for numeric salary
    private String skills;
    private String postingDate;

    // Constructor without jobType, using salaryAmount
    public Job(String jobId, String title, String description, String company, String location,
               double salaryAmount, String skills, String postingDate) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.company = company;
        this.location = location;
        this.salaryAmount = salaryAmount;
        this.skills = skills;
        this.postingDate = postingDate;
    }

    // Getters and setters
    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getSalaryAmount() { return salaryAmount; } // Getter for salaryAmount
    public void setSalaryAmount(double salaryAmount) { this.salaryAmount = salaryAmount; } // Setter for salaryAmount

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getPostingDate() { return postingDate; }
    public void setPostingDate(String postingDate) { this.postingDate = postingDate; }

    // Display formatted job information (without Job Type)
    @Override
    public String toString() {
        return String.format(
            "Job ID: %s\nTitle: %s\nDescription: %s\nCompany: %s\nLocation: %s\n" +
            "Salary: RM%.2f\nSkills: %s\nPosting Date: %s", // Display salary with RM
            jobId, title, description, company, location, salaryAmount, skills, postingDate);
    }

    // Simple equality check based on Job ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job other = (Job) o;
        return jobId != null && jobId.equals(other.jobId);
    }

    // Simple hashcode implementation
    @Override
    public int hashCode() {
        return jobId != null ? jobId.hashCode() : 0;
    }
}
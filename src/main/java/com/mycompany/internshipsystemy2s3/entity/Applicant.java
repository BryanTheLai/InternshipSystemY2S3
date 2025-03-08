package com.mycompany.internshipsystemy2s3.entity;

public class Applicant {
    private String id;
    private String name;
    private String location;
    private String skill;
    private String university;
    private String major;
    private double gpa;
    private String graduationYear;
    private String applicationStatus;
    
    // Full constructor
    public Applicant(String id, String name, String location, String skill,
                    String university, String major, double gpa, String graduationYear,
                    String applicationStatus) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.skill = skill;
        this.university = university;
        this.major = major;
        this.gpa = gpa;
        this.graduationYear = graduationYear;
        this.applicationStatus = applicationStatus;
    }
    
    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getSkill() { return skill; }
    public void setSkill(String skill) { this.skill = skill; }
    
    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }
    
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    
    public String getGraduationYear() { return graduationYear; }
    public void setGraduationYear(String graduationYear) { this.graduationYear = graduationYear; }
    
    public String getApplicationStatus() { return applicationStatus; }
    public void setApplicationStatus(String applicationStatus) { this.applicationStatus = applicationStatus; }

    // Display formatted applicant information
    @Override
    public String toString() {
        return String.format(
            "ID: %s\nName: %s\nLocation: %s\nSkill: %s\nUniversity: %s\n" +
            "Major: %s\nGPA: %.2f\nGraduation Year: %s\nApplication Status: %s",
            id, name, location, skill, university, major, gpa, graduationYear, applicationStatus);
    }

    // Simple equality check based on ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant other = (Applicant) o;
        return id != null && id.equals(other.id);
    }

    // Simple hashcode implementation
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
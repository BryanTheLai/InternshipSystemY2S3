package com.mycompany.internshipsystemy2s3.entity;

import java.util.Objects;

public class Applicant {
    private String id;
    private String name;
    private String location;
    private String desiredJobType;
    private String skill;
    
    // Additional fields relevant for internship applicants
    private String university;
    private String major;
    private double gpa;
    private String graduationYear;
    private String applicationStatus;

    // Constructor
    public Applicant(String id, String name, String location, String desiredJobType, String skill) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.desiredJobType = desiredJobType;
        this.skill = skill;
        this.applicationStatus = "Applied"; // Default status
    }
    
    // Full constructor with all fields
    public Applicant(String id, String name, String location, String desiredJobType, String skill,
                    String university, String major, double gpa, String graduationYear,
                    String applicationStatus) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.desiredJobType = desiredJobType;
        this.skill = skill;
        this.university = university;
        this.major = major;
        this.gpa = gpa;
        this.graduationYear = graduationYear;
        this.applicationStatus = applicationStatus;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesiredJobType() {
        return desiredJobType;
    }

    public void setDesiredJobType(String desiredJobType) {
        this.desiredJobType = desiredJobType;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
    
    public String getUniversity() {
        return university;
    }
    
    public void setUniversity(String university) {
        this.university = university;
    }
    
    public String getMajor() {
        return major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    public String getGraduationYear() {
        return graduationYear;
    }
    
    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }
    
    public String getApplicationStatus() {
        return applicationStatus;
    }
    
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Applicant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", desiredJobType='" + desiredJobType + '\'' +
                ", skill='" + skill + '\'' +
                (university != null ? ", university='" + university + '\'' : "") +
                (major != null ? ", major='" + major + '\'' : "") +
                (gpa > 0 ? ", gpa=" + gpa : "") +
                (graduationYear != null ? ", graduationYear='" + graduationYear + '\'' : "") +
                (applicationStatus != null ? ", applicationStatus='" + applicationStatus + '\'' : "") +
                '}';
    }

    // Override equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(id, applicant.id) &&
               Objects.equals(name, applicant.name) &&
               Objects.equals(location, applicant.location) &&
               Objects.equals(desiredJobType, applicant.desiredJobType) &&
               Objects.equals(skill, applicant.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, desiredJobType, skill);
    }
}
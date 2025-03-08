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
    
    // Full constructor with all fields
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
    
    // Basic constructor for non-internship applicants
    public Applicant(String id, String name, String location, String skill) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.skill = skill;
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
                ", skill='" + skill + '\'' +
                (university != null ? ", university='" + university + '\'' : "") +
                (major != null ? ", major='" + major + '\'' : "") +
                (gpa > 0 ? ", gpa=" + gpa : "") +
                (graduationYear != null ? ", graduationYear='" + graduationYear + '\'' : "") +
                (applicationStatus != null ? ", applicationStatus='" + applicationStatus + '\'' : "") +
                '}';
    }

    // Override equals method without using java.util.Objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return equals(id, applicant.id) &&
               equals(name, applicant.name) &&
               equals(location, applicant.location) &&
               equals(skill, applicant.skill);
    }

    // Custom null-safe equality check
    private boolean equals(Object a, Object b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    // Override hashCode without using java.util.Objects
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (skill != null ? skill.hashCode() : 0);
        return result;
    }
}
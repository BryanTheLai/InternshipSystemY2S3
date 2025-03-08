package com.mycompany.internshipsystemy2s3.dao;

import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.entity.Applicant;

public class ApplicantDAO {
    private ListInterface<Applicant> applicants;
    private int nextId;

    // Selection options
    public static final String[] MALAYSIAN_UNIVERSITIES = {
        "Universiti Malaya", "Universiti Sains Malaysia", "Universiti Kebangsaan Malaysia",
        "Universiti Putra Malaysia", "Universiti Teknologi Malaysia", "Universiti Teknologi MARA",
        "Universiti Islam Antarabangsa Malaysia", "HELP University", "Taylor's University", 
        "Sunway University", "Multimedia University", "Asia Pacific University",
        "TARUMT", "Universiti Tenaga Nasional", "Monash University Malaysia"
    };
    
    public static final String[] MAJORS = {
        "Computer Science", "Software Engineering", "Information Technology", 
        "Computer Engineering", "Artificial Intelligence", "Data Science",
        "Cybersecurity", "Mobile Computing", "Information Systems", 
        "Multimedia Design", "Network Engineering", "Web Development"
    };

    public ApplicantDAO() {
        this.applicants = new DoublyLinkedList<>();
        this.nextId = 1;
        prefillData();
    }

    public String generateId() {
        return String.format("ID%03d", nextId++);
    }

    private void prefillData() {
        // Add sample applicants
        addSampleApplicant("ID001", "Alice Smith", "Shah Alam", "DevOps", "Universiti Malaya", 
                          "Computer Engineering", 3.6, "2023", "Interview");
        addSampleApplicant("ID002", "Bob Johnson", "Cyberjaya", "Frontend", "HELP University", 
                          "Software Engineering", 3.4, "2024", "Applied");
        addSampleApplicant("ID003", "Charlie Brown", "Petaling Jaya", "Backend", "Sunway University", 
                          "Computer Science", 3.9, "2023", "Shortlisted");
        addSampleApplicant("ID004", "David Wilson", "Bangi", "Fullstack", "Universiti Kebangsaan Malaysia", 
                          "Information Technology", 3.5, "2024", "Pending");
        addSampleApplicant("ID005", "Eve Davis", "Setapak", "Mobile", "Asia Pacific University", 
                          "Mobile Computing", 3.7, "2023", "Applied");
        addSampleApplicant("ID006", "Frank Miller", "Shah Alam", "Data Science", "Universiti Teknologi MARA", 
                          "Computer Science", 3.8, "2024", "Applied");
        addSampleApplicant("ID007", "Grace Lee", "Cyberjaya", "Machine Learning", "Multimedia University", 
                          "Artificial Intelligence", 3.7, "2025", "Pending");
        addSampleApplicant("ID008", "Hank Martin", "Setapak", "Cloud Engineering", "TARUMT", 
                          "Information Technology", 3.5, "2024", "Applied");
        addSampleApplicant("ID009", "Ivy Clark", "Kajang", "Security", "Universiti Tenaga Nasional", 
                          "Cybersecurity", 3.9, "2023", "Shortlisted");
        addSampleApplicant("ID010", "Jack Lewis", "Seri Kembangan", "Database Administration", "Universiti Putra Malaysia", 
                          "Information Systems", 3.6, "2024", "Interview");
        addSampleApplicant("ID011", "Michelle Tan", "Subang Jaya", "UI/UX Design", "Taylor's University", 
                          "Multimedia Design", 3.7, "2025", "Applied");
        addSampleApplicant("ID012", "Nizam Abdullah", "Petaling Jaya", "Web Development", "Monash University Malaysia", 
                          "Software Engineering", 3.8, "2024", "Pending");
                                    
        this.nextId = 13;
    }
    
    private void addSampleApplicant(String id, String name, String location, String skill, 
                                   String university, String major, double gpa, 
                                   String gradYear, String status) {
        applicants.add(new Applicant(id, name, location, skill, university, major, gpa, gradYear, status));
    }

    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }

    public void updateApplicant(String id, Applicant updatedApplicant) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.add(i, updatedApplicant);
                applicants.remove(i + 1);
                return;
            }
        }
    }

    public void removeApplicant(String id) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.remove(i);
                return;
            }
        }
    }

    public ListInterface<Applicant> filterApplicants(FilterCriteria criteria) {
        ListInterface<Applicant> filteredApplicants = new DoublyLinkedList<>();
        for (int i = 0; i < applicants.size(); i++) {
            Applicant applicant = applicants.get(i);
            if (criteria.matches(applicant)) {
                filteredApplicants.add(applicant);
            }
        }
        return filteredApplicants;
    }

    public ListInterface<Applicant> getAllApplicants() {
        return applicants;
    }

    public interface FilterCriteria {
        boolean matches(Applicant applicant);
    }
}
package com.mycompany.internshipsystemy2s3.dao;

import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.entity.Applicant;

public class ApplicantDAO {
    // Use interface-based declaration
    private ListInterface<Applicant> applicants;

    public ApplicantDAO() {
        // Initialize with concrete implementation
        this.applicants = new DoublyLinkedList<>();
        prefillData();
    }

    private void prefillData() {
        // Sample data with Malaysian universities and locations
        applicants.add(new Applicant("ID1", "Alice Smith", "Shah Alam", "DevOps",
                                    "Universiti Malaya", "Computer Engineering", 3.6, "2023", "Interview"));
                                    
        applicants.add(new Applicant("ID2", "Bob Johnson", "Cyberjaya", "Frontend",
                                    "HELP University", "Software Engineering", 3.4, "2024", "Applied"));
                                    
        applicants.add(new Applicant("ID3", "Charlie Brown", "Petaling Jaya", "Backend",
                                    "Sunway University", "Computer Science", 3.9, "2023", "Shortlisted"));
                                    
        applicants.add(new Applicant("ID4", "David Wilson", "Bangi", "Fullstack",
                                    "Universiti Kebangsaan Malaysia", "Information Technology", 3.5, "2024", "Pending"));
                                    
        applicants.add(new Applicant("ID5", "Eve Davis", "Setapak", "Mobile",
                                    "Asia Pacific University", "Mobile Computing", 3.7, "2023", "Applied"));
        
        // Add more detailed applicants
        applicants.add(new Applicant("ID6", "Frank Miller", "Shah Alam", "Data Science",
                                    "Universiti Teknologi MARA", "Computer Science", 3.8, "2024", "Applied"));
        
        applicants.add(new Applicant("ID7", "Grace Lee", "Cyberjaya", "Machine Learning",
                                    "Multimedia University", "Artificial Intelligence", 3.7, "2025", "Pending"));
        
        applicants.add(new Applicant("ID8", "Hank Martin", "Setapak", "Cloud Engineering",
                                    "TARUMT", "Information Technology", 3.5, "2024", "Applied"));
        
        applicants.add(new Applicant("ID9", "Ivy Clark", "Kajang", "Security",
                                    "Universiti Tenaga Nasional", "Cybersecurity", 3.9, "2023", "Shortlisted"));
        
        applicants.add(new Applicant("ID10", "Jack Lewis", "Seri Kembangan", "Database Administration",
                                    "Universiti Putra Malaysia", "Information Systems", 3.6, "2024", "Interview"));
        
        applicants.add(new Applicant("ID11", "Michelle Tan", "Subang Jaya", "UI/UX Design",
                                    "Taylor's University", "Multimedia Design", 3.7, "2025", "Applied"));
        
        applicants.add(new Applicant("ID12", "Nizam Abdullah", "Petaling Jaya", "Web Development",
                                    "Monash University Malaysia", "Software Engineering", 3.8, "2024", "Pending"));
    }

    // Add a new applicant
    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }

    // Update an existing applicant by ID
    public void updateApplicant(String id, Applicant updatedApplicant) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.add(i, updatedApplicant);
                applicants.remove(i + 1);
                return;
            }
        }
    }

    // Remove an applicant by ID
    public void removeApplicant(String id) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.remove(i);
                return;
            }
        }
    }

    // Filter applicants based on criteria
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

    // Get all applicants
    public ListInterface<Applicant> getAllApplicants() {
        return applicants;
    }

    // Filter criteria interface
    public interface FilterCriteria {
        boolean matches(Applicant applicant);
    }
}
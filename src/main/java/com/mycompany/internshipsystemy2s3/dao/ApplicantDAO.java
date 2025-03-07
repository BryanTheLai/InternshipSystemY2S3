package com.mycompany.internshipsystemy2s3.dao;

import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;
import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedListInterface;
import com.mycompany.internshipsystemy2s3.entity.Applicant;

public class ApplicantDAO {
    private DoublyLinkedListInterface<Applicant> applicants;

    public ApplicantDAO() {
        this.applicants = new DoublyLinkedList<>();
        prefillData();
    }

    private void prefillData() {
        // Adding sample data with Malaysian universities and locations
        applicants.add(new Applicant("ID1", "Alice Smith", "Shah Alam", "Full-time", "DevOps"));
        applicants.add(new Applicant("ID2", "Bob Johnson", "Cyberjaya", "Part-time", "Frontend"));
        applicants.add(new Applicant("ID3", "Charlie Brown", "Petaling Jaya", "Internship", "Backend"));
        applicants.add(new Applicant("ID4", "David Wilson", "Bangi", "Full-time", "Fullstack"));
        applicants.add(new Applicant("ID5", "Eve Davis", "Setapak", "Part-time", "Mobile"));
        
        // Add more detailed applicant for internship module
        applicants.add(new Applicant("ID6", "Frank Miller", "Shah Alam", "Internship", "Data Science",
                                     "Universiti Teknologi MARA", "Computer Science", 3.8, "2024", "Applied"));
        
        applicants.add(new Applicant("ID7", "Grace Lee", "Cyberjaya", "Internship", "Machine Learning",
                                     "Multimedia University", "Artificial Intelligence", 3.7, "2025", "Pending"));
        
        applicants.add(new Applicant("ID8", "Hank Martin", "Setapak", "Part-time", "Cloud Engineering",
                                     "TARUMT", "Information Technology", 3.5, "2024", "Applied"));
        
        applicants.add(new Applicant("ID9", "Ivy Clark", "Kajang", "Internship", "Security",
                                     "Universiti Tenaga Nasional", "Cybersecurity", 3.9, "2023", "Shortlisted"));
        
        applicants.add(new Applicant("ID10", "Jack Lewis", "Seri Kembangan", "Full-time", "Database Administration",
                                     "Universiti Putra Malaysia", "Information Systems", 3.6, "2024", "Interview"));
        
        applicants.add(new Applicant("ID11", "Michelle Tan", "Subang Jaya", "Internship", "UI/UX Design",
                                     "Taylor's University", "Multimedia Design", 3.7, "2025", "Applied"));
        
        applicants.add(new Applicant("ID12", "Nizam Abdullah", "Petaling Jaya", "Part-time", "Web Development",
                                     "Monash University Malaysia", "Software Engineering", 3.8, "2024", "Pending"));
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

    public DoublyLinkedListInterface<Applicant> filterApplicants(FilterCriteria criteria) {
        DoublyLinkedListInterface<Applicant> filteredApplicants = new DoublyLinkedList<>();
        for (int i = 0; i < applicants.size(); i++) {
            Applicant applicant = applicants.get(i);
            if (criteria.matches(applicant)) {
                filteredApplicants.add(applicant);
            }
        }
        return filteredApplicants;
    }

    public DoublyLinkedListInterface<Applicant> getAllApplicants() {
        return applicants;
    }

    public interface FilterCriteria {
        boolean matches(Applicant applicant);
    }
}
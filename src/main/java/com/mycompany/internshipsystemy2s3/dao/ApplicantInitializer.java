package com.mycompany.internshipsystemy2s3.dao;

import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import com.mycompany.internshipsystemy2s3.entity.Applicant;

/**
 * Class responsible for initializing sample applicant data
 */
public class ApplicantInitializer {

    // Selection options - moved from ApplicantDAO for better organization
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

    /**
     * Creates and populates a list with sample applicant data
     * 
     * @return ListInterface containing sample applicants
     */
    public ListInterface<Applicant> initializeApplicants() {
        ListInterface<Applicant> applicants = new DoublyLinkedList<>();

        // Add sample applicants
        applicants.add(new Applicant("ID001", "Alice Smith", "Shah Alam", "Frontend",
                "Universiti Malaya", "Computer Science", 3.75, "2023", "Applied"));

        applicants.add(new Applicant("ID002", "Bob Johnson", "Cyberjaya", "Backend",
                "HELP University", "Software Engineering", 3.43, "2024", "Pending"));

        applicants.add(new Applicant("ID003", "Charlie Wong", "Petaling Jaya", "Fullstack",
                "Taylor's University", "Information Technology", 3.88, "2023", "Interview"));

        applicants.add(new Applicant("ID004", "David Lee", "Bangi", "Mobile",
                "Universiti Kebangsaan Malaysia", "Computer Engineering", 3.25, "2025", "Applied"));

        applicants.add(new Applicant("ID005", "Emma Tan", "Setapak", "Data Science",
                "Sunway University", "Data Science", 3.92, "2023", "Offered"));

        applicants.add(new Applicant("ID006", "Fatima Zahra", "Shah Alam", "Cybersecurity",
                "Universiti Teknologi MARA", "Cybersecurity", 3.6, "2024", "Interview"));

        applicants.add(new Applicant("ID007", "Gabriel Lim", "Kajang", "DevOps",
                "Multimedia University", "Software Engineering", 3.45, "2024", "Pending"));

        applicants.add(new Applicant("ID008", "Hana Ibrahim", "Subang Jaya", "UI/UX Design",
                "Asia Pacific University", "Multimedia Design", 3.7, "2023", "Offered"));

        applicants.add(new Applicant("ID009", "Isaac Chen", "Kota Damansara", "Cloud Engineering",
                "TARUMT", "Information Systems", 3.5, "2025", "Applied"));

        applicants.add(new Applicant("ID010", "Julia Ahmad", "Semenyih", "Machine Learning",
                "Universiti Putra Malaysia", "Artificial Intelligence", 3.85, "2023", "Interview"));

        applicants.add(new Applicant("ID011", "Kevin Raj", "Cyberjaya", "Web Development",
                "Monash University Malaysia", "Web Development", 3.36, "2024", "Pending"));

        applicants.add(new Applicant("ID012", "Lily Phua", "Shah Alam", "Database Administration",
                "Universiti Teknologi Malaysia", "Information Systems", 3.62, "2023", "Applied"));

        return applicants;
    }

    /**
     * Test method to demonstrate the initializer functionality
     */
    public static void main(String[] args) {
        ApplicantInitializer initializer = new ApplicantInitializer();
        ListInterface<Applicant> applicants = initializer.initializeApplicants();

        System.out.println("\nApplicants:");
        for (int i = 0; i < applicants.size(); i++) {
            System.out.println(applicants.get(i));
        }
    }
}
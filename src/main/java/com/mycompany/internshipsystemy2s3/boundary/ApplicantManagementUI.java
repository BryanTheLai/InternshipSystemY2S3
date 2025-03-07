package com.mycompany.internshipsystemy2s3.boundary;

import com.mycompany.internshipsystemy2s3.control.ApplicantManagement;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.dao.ApplicantDAO.FilterCriteria;
import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedListInterface;
import java.util.Scanner;

public class ApplicantManagementUI {
    private ApplicantManagement applicantManagement;
    private Scanner scanner;

    // Malaysian locations
    private static final String[] LOCATIONS = {
        "Shah Alam", "Cyberjaya", "Petaling Jaya", "Bangi", "Setapak", 
        "Seri Kembangan", "Kajang", "Subang Jaya", "Kota Damansara", 
        "Salak Tinggi", "Semenyih"
    };

    // Job types
    private static final String[] JOB_TYPES = {
        "Full-time", "Part-time", "Internship"
    };

    // Skills
    private static final String[] SKILLS = {
        "Frontend", "Backend", "Fullstack", "DevOps", "Mobile", "Data Science",
        "Machine Learning", "Cloud Engineering", "Security", "Database Administration",
        "UI/UX Design", "Web Development", "Artificial Intelligence", "Cybersecurity"
    };

    public ApplicantManagementUI() {
        this.applicantManagement = new ApplicantManagement();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nApplicant Management System");
            System.out.println("1. Add Applicant");
            System.out.println("2. Update Applicant");
            System.out.println("3. Remove Applicant");
            System.out.println("4. Filter Applicants");
            System.out.println("5. Display All Applicants");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addApplicant();
                    break;
                case 2:
                    updateApplicant();
                    break;
                case 3:
                    removeApplicant();
                    break;
                case 4:
                    filterApplicants();
                    break;
                case 5:
                    displayAllApplicants();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addApplicant() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.println("Available Locations:");
        for (int i = 0; i < LOCATIONS.length; i++) {
            System.out.println((i+1) + ". " + LOCATIONS[i]);
        }
        System.out.print("Select Location (enter number): ");
        int locationChoice = Integer.parseInt(scanner.nextLine());
        String location = LOCATIONS[locationChoice-1];

        System.out.println("Available Job Types:");
        for (int i = 0; i < JOB_TYPES.length; i++) {
            System.out.println((i+1) + ". " + JOB_TYPES[i]);
        }
        System.out.print("Select Desired Job Type (enter number): ");
        int jobChoice = Integer.parseInt(scanner.nextLine());
        String desiredJobType = JOB_TYPES[jobChoice-1];

        System.out.println("Available Skills:");
        for (int i = 0; i < SKILLS.length; i++) {
            System.out.println((i+1) + ". " + SKILLS[i]);
        }
        System.out.print("Select Skill (enter number): ");
        int skillChoice = Integer.parseInt(scanner.nextLine());
        String skill = SKILLS[skillChoice-1];

        // For internship applicants, collect additional information
        if (desiredJobType.equals("Internship")) {
            System.out.println("Enter additional information for internship applicant:");
            
            System.out.print("University: ");
            String university = scanner.nextLine();
            
            System.out.print("Major: ");
            String major = scanner.nextLine();
            
            System.out.print("GPA: ");
            double gpa = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Graduation Year: ");
            String gradYear = scanner.nextLine();
            
            Applicant applicant = new Applicant(id, name, location, desiredJobType, skill,
                                               university, major, gpa, gradYear, "Applied");
            applicantManagement.addApplicant(applicant);
        } else {
            Applicant applicant = new Applicant(id, name, location, desiredJobType, skill);
            applicantManagement.addApplicant(applicant);
        }
        
        System.out.println("Applicant added successfully.");
    }

    private void updateApplicant() {
        System.out.print("Enter ID of the applicant to update: ");
        String id = scanner.nextLine();

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();

        System.out.println("Available Locations:");
        for (int i = 0; i < LOCATIONS.length; i++) {
            System.out.println((i+1) + ". " + LOCATIONS[i]);
        }
        System.out.print("Select New Location (enter number): ");
        int locationChoice = Integer.parseInt(scanner.nextLine());
        String location = LOCATIONS[locationChoice-1];

        System.out.println("Available Job Types:");
        for (int i = 0; i < JOB_TYPES.length; i++) {
            System.out.println((i+1) + ". " + JOB_TYPES[i]);
        }
        System.out.print("Select New Desired Job Type (enter number): ");
        int jobChoice = Integer.parseInt(scanner.nextLine());
        String desiredJobType = JOB_TYPES[jobChoice-1];

        System.out.println("Available Skills:");
        for (int i = 0; i < SKILLS.length; i++) {
            System.out.println((i+1) + ". " + SKILLS[i]);
        }
        System.out.print("Select New Skill (enter number): ");
        int skillChoice = Integer.parseInt(scanner.nextLine());
        String skill = SKILLS[skillChoice-1];

        // For internship applicants, collect additional information
        if (desiredJobType.equals("Internship")) {
            System.out.println("Enter additional information for internship applicant:");
            
            System.out.print("University: ");
            String university = scanner.nextLine();
            
            System.out.print("Major: ");
            String major = scanner.nextLine();
            
            System.out.print("GPA: ");
            double gpa = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Graduation Year: ");
            String gradYear = scanner.nextLine();
            
            System.out.print("Application Status: ");
            String status = scanner.nextLine();
            
            Applicant updatedApplicant = new Applicant(id, name, location, desiredJobType, skill,
                                                     university, major, gpa, gradYear, status);
            applicantManagement.updateApplicant(id, updatedApplicant);
        } else {
            Applicant updatedApplicant = new Applicant(id, name, location, desiredJobType, skill);
            applicantManagement.updateApplicant(id, updatedApplicant);
        }
        
        System.out.println("Applicant updated successfully.");
    }

    private void removeApplicant() {
        System.out.print("Enter ID of the applicant to remove: ");
        String id = scanner.nextLine();
        applicantManagement.removeApplicant(id);
        System.out.println("Applicant removed successfully.");
    }

    private void filterApplicants() {
        System.out.print("Enter Location to filter by (leave blank for no filter): ");
        String locationInput = scanner.nextLine();
        System.out.print("Enter Desired Job Type to filter by (leave blank for no filter): ");
        String jobTypeInput = scanner.nextLine();
        System.out.print("Enter Skill to filter by (leave blank for no filter): ");
        String skillInput = scanner.nextLine();

        FilterCriteria criteria = applicant -> {
            boolean matches = true;
            if (!locationInput.isEmpty()) {
                matches &= applicant.getLocation().equalsIgnoreCase(locationInput);
            }
            if (!jobTypeInput.isEmpty()) {
                matches &= applicant.getDesiredJobType().equalsIgnoreCase(jobTypeInput);
            }
            if (!skillInput.isEmpty()) {
                matches &= applicant.getSkill().equalsIgnoreCase(skillInput);
            }
            return matches;
        };

        DoublyLinkedListInterface<Applicant> filteredApplicants = applicantManagement.filterApplicants(criteria);

        System.out.println("Filtered Applicants:");
        for (int i = 0; i < filteredApplicants.size(); i++) {
            System.out.println(filteredApplicants.get(i));
        }
    }

    private void displayAllApplicants() {
        DoublyLinkedListInterface<Applicant> allApplicants = applicantManagement.getAllApplicants();
        System.out.println("All Applicants:");
        for (int i = 0; i < allApplicants.size(); i++) {
            System.out.println(allApplicants.get(i));
        }
    }

    public static void main(String[] args) {
        ApplicantManagementUI ui = new ApplicantManagementUI();
        ui.displayMenu();
    }
}
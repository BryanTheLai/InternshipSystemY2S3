package com.mycompany.internshipsystemy2s3.boundary;

import com.mycompany.internshipsystemy2s3.control.ApplicantManagement;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.dao.ApplicantDAO.FilterCriteria;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
// Replace java.util.Scanner with standard Java IO
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ApplicantManagementUI {
    private ApplicantManagement applicantManagement;
    private BufferedReader reader; // Replace Scanner with BufferedReader

    // Malaysian locations
    private static final String[] LOCATIONS = {
        "Shah Alam", "Cyberjaya", "Petaling Jaya", "Bangi", "Setapak", 
        "Seri Kembangan", "Kajang", "Subang Jaya", "Kota Damansara", 
        "Salak Tinggi", "Semenyih"
    };

    // Skills
    private static final String[] SKILLS = {
        "Frontend", "Backend", "Fullstack", "DevOps", "Mobile", "Data Science",
        "Machine Learning", "Cloud Engineering", "Security", "Database Administration",
        "UI/UX Design", "Web Development", "Artificial Intelligence", "Cybersecurity"
    };

    public ApplicantManagementUI() {
        this.applicantManagement = new ApplicantManagement();
        // Use BufferedReader instead of Scanner
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    // Helper method to read input (replacing scanner.nextLine())
    private String readInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
            return "";
        }
    }

    // Helper method to read integer input
    private int readIntInput() {
        try {
            return Integer.parseInt(readInput());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please try again.");
            return readIntInput();
        }
    }

    // Helper method to read double input
    private double readDoubleInput() {
        try {
            return Double.parseDouble(readInput());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please try again.");
            return readDoubleInput();
        }
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
            
            int choice = readIntInput();

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
        String id = readInput();
        
        System.out.print("Enter Name: ");
        String name = readInput();

        System.out.println("Available Locations:");
        for (int i = 0; i < LOCATIONS.length; i++) {
            System.out.println((i+1) + ". " + LOCATIONS[i]);
        }
        System.out.print("Select Location (enter number): ");
        int locationChoice = readIntInput();
        String location = LOCATIONS[locationChoice-1];

        System.out.println("Available Skills:");
        for (int i = 0; i < SKILLS.length; i++) {
            System.out.println((i+1) + ". " + SKILLS[i]);
        }
        System.out.print("Select Skill (enter number): ");
        int skillChoice = readIntInput();
        String skill = SKILLS[skillChoice-1];

        System.out.print("Is this an internship applicant? (y/n): ");
        String isInternship = readInput();

        if (isInternship.equalsIgnoreCase("y")) {
            System.out.println("Enter additional information for internship applicant:");
            
            System.out.print("University: ");
            String university = readInput();
            
            System.out.print("Major: ");
            String major = readInput();
            
            System.out.print("GPA: ");
            double gpa = readDoubleInput();
            
            System.out.print("Graduation Year: ");
            String gradYear = readInput();
            
            // Create Applicant with full constructor
            Applicant applicant = new Applicant(id, name, location, skill,
                                               university, major, gpa, gradYear, "Applied");
            applicantManagement.addApplicant(applicant);
        } else {
            // Create basic Applicant
            Applicant applicant = new Applicant(id, name, location, skill);
            applicantManagement.addApplicant(applicant);
        }
        
        System.out.println("Applicant added successfully.");
    }

    private void updateApplicant() {
        System.out.print("Enter ID of the applicant to update: ");
        String id = readInput();

        System.out.print("Enter New Name: ");
        String name = readInput();

        System.out.println("Available Locations:");
        for (int i = 0; i < LOCATIONS.length; i++) {
            System.out.println((i+1) + ". " + LOCATIONS[i]);
        }
        System.out.print("Select New Location (enter number): ");
        int locationChoice = readIntInput();
        String location = LOCATIONS[locationChoice-1];

        System.out.println("Available Skills:");
        for (int i = 0; i < SKILLS.length; i++) {
            System.out.println((i+1) + ". " + SKILLS[i]);
        }
        System.out.print("Select New Skill (enter number): ");
        int skillChoice = readIntInput();
        String skill = SKILLS[skillChoice-1];

        System.out.print("Is this an internship applicant? (y/n): ");
        String isInternship = readInput();

        if (isInternship.equalsIgnoreCase("y")) {
            System.out.println("Enter additional information for internship applicant:");
            
            System.out.print("University: ");
            String university = readInput();
            
            System.out.print("Major: ");
            String major = readInput();
            
            System.out.print("GPA: ");
            double gpa = readDoubleInput();
            
            System.out.print("Graduation Year: ");
            String gradYear = readInput();
            
            System.out.print("Application Status: ");
            String status = readInput();
            
            Applicant updatedApplicant = new Applicant(id, name, location, skill,
                                                     university, major, gpa, gradYear, status);
            applicantManagement.updateApplicant(id, updatedApplicant);
        } else {
            Applicant updatedApplicant = new Applicant(id, name, location, skill);
            applicantManagement.updateApplicant(id, updatedApplicant);
        }
        
        System.out.println("Applicant updated successfully.");
    }

    private void removeApplicant() {
        System.out.print("Enter ID of the applicant to remove: ");
        String id = readInput();
        applicantManagement.removeApplicant(id);
        System.out.println("Applicant removed successfully.");
    }

    private void filterApplicants() {
        System.out.print("Enter Location to filter by (leave blank for no filter): ");
        String locationInput = readInput();
        System.out.print("Enter Skill to filter by (leave blank for no filter): ");
        String skillInput = readInput();

        FilterCriteria criteria = applicant -> {
            boolean matches = true;
            if (!locationInput.isEmpty()) {
                matches &= applicant.getLocation().equalsIgnoreCase(locationInput);
            }
            if (!skillInput.isEmpty()) {
                matches &= applicant.getSkill().equalsIgnoreCase(skillInput);
            }
            return matches;
        };

        ListInterface<Applicant> filteredApplicants = applicantManagement.filterApplicants(criteria);

        System.out.println("Filtered Applicants:");
        for (int i = 0; i < filteredApplicants.size(); i++) {
            System.out.println(filteredApplicants.get(i));
        }
    }

    private void displayAllApplicants() {
        ListInterface<Applicant> allApplicants = applicantManagement.getAllApplicants();
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
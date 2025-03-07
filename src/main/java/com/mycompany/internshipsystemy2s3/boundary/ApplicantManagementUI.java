package com.mycompany.internshipsystemy2s3.boundary;

import com.mycompany.internshipsystemy2s3.control.ApplicantManagement;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.entity.Location;
import com.mycompany.internshipsystemy2s3.entity.JobType;
import com.mycompany.internshipsystemy2s3.entity.Skill;
import com.mycompany.internshipsystemy2s3.dao.ApplicantDAO.FilterCriteria;
import java.util.Scanner;

import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;

public class ApplicantManagementUI {
    private ApplicantManagement applicantManagement;
    private Scanner scanner;

    public ApplicantManagementUI() {
        this.applicantManagement = new ApplicantManagement();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Applicant Management System");
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
        for (Location location : Location.values()) {
            System.out.println(location);
        }
        System.out.print("Enter Location (e.g., NEW_YORK): ");
        Location location = Location.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Available Job Types:");
        for (JobType jobType : JobType.values()) {
            System.out.println(jobType);
        }
        System.out.print("Enter Desired Job Type (e.g., FULL_TIME): ");
        JobType desiredJobType = JobType.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Available Skills:");
        for (Skill skill : Skill.values()) {
            System.out.println(skill);
        }
        System.out.print("Enter Skill (e.g., DEVOPS): ");
        Skill skill = Skill.valueOf(scanner.nextLine().toUpperCase());

        Applicant applicant = new Applicant(id, name, location, desiredJobType, skill);
        applicantManagement.addApplicant(applicant);
        System.out.println("Applicant added successfully.");
    }

    private void updateApplicant() {
        System.out.print("Enter ID of the applicant to update: ");
        String id = scanner.nextLine();

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();

        System.out.println("Available Locations:");
        for (Location location : Location.values()) {
            System.out.println(location);
        }
        System.out.print("Enter New Location (e.g., NEW_YORK): ");
        Location location = Location.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Available Job Types:");
        for (JobType jobType : JobType.values()) {
            System.out.println(jobType);
        }
        System.out.print("Enter New Desired Job Type (e.g., FULL_TIME): ");
        JobType desiredJobType = JobType.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Available Skills:");
        for (Skill skill : Skill.values()) {
            System.out.println(skill);
        }
        System.out.print("Enter New Skill (e.g., DEVOPS): ");
        Skill skill = Skill.valueOf(scanner.nextLine().toUpperCase());

        Applicant updatedApplicant = new Applicant(id, name, location, desiredJobType, skill);
        applicantManagement.updateApplicant(id, updatedApplicant);
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
                matches &= applicant.getLocation().name().equalsIgnoreCase(locationInput);
            }
            if (!jobTypeInput.isEmpty()) {
                matches &= applicant.getDesiredJobType().name().equalsIgnoreCase(jobTypeInput);
            }
            if (!skillInput.isEmpty()) {
                matches &= applicant.getSkill().name().equalsIgnoreCase(skillInput);
            }
            return matches;
        };

        DoublyLinkedList<Applicant> filteredApplicants = applicantManagement.filterApplicants(criteria);

        System.out.println("Filtered Applicants:");
        for (int i = 0; i < filteredApplicants.size(); i++) {
            System.out.println(filteredApplicants.get(i));
        }
    }

    private void displayAllApplicants() {
        DoublyLinkedList<Applicant> allApplicants = applicantManagement.getAllApplicants();
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
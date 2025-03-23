package com.mycompany.internshipsystemy2s3.boundary;

import com.mycompany.internshipsystemy2s3.control.ApplicantControllerInterface;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.dao.ApplicantDAOInterface.FilterCriteria;
import com.mycompany.internshipsystemy2s3.dao.ApplicantInitializer;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import java.util.Scanner;

/**
 * User interface for the applicant management system.
 * Handles presentation logic and user interaction.
 */
public class ApplicantManagementUI {
    private ApplicantControllerInterface applicantManagement;
    private Scanner scanner;

    // UI selection arrays - moved from DAO to UI where they belong
    private static final String[] LOCATIONS = {
            "Shah Alam", "Cyberjaya", "Petaling Jaya", "Bangi", "Setapak",
            "Seri Kembangan", "Kajang", "Subang Jaya", "Kota Damansara",
            "Salak Tinggi", "Semenyih"
    };

    private static final String[] SKILLS = {
            "Frontend", "Backend", "Fullstack", "DevOps", "Mobile", "Data Science",
            "Machine Learning", "Cloud Engineering", "Security", "Database Administration",
            "UI/UX Design", "Web Development", "Artificial Intelligence", "Cybersecurity"
    };

    private static final String[] STATUS_OPTIONS = {
            "Applied", "Pending", "Shortlisted", "Interview", "Offered", "Rejected"
    };

    /**
     * Constructor that accepts controller reference
     * 
     * @param controller The controller this UI will use
     */
    public ApplicantManagementUI(ApplicantControllerInterface controller) {
        this.applicantManagement = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Default constructor for when no controller is provided yet
     */
    public ApplicantManagementUI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Sets the controller for this UI
     * 
     * @param controller The controller to use
     */
    public void setController(ApplicantControllerInterface controller) {
        this.applicantManagement = controller;
    }

    /**
     * Displays the main menu and handles user navigation
     */
    public void displayMainMenu() {
        String[] mainOptions = {
                "Add New Applicant",
                "Update Existing Applicant",
                "Remove Applicant",
                "Filter Applicants",
                "Display All Applicants",
                "Exit"
        };

        boolean running = true;
        while (running) {
            System.out.println("\n========= INTERNSHIP APPLICANT MANAGEMENT SYSTEM =========");
            int choice = displayMenu("MAIN MENU", mainOptions);

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
                    System.out.println("\nExiting system. Goodbye!");
                    running = false;
                    break;
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                readInput();
            }
        }
    }

    // UI helper methods

    /**
     * Formats an applicant for display
     */
    private String formatApplicant(Applicant app) {
        return String.format("%-6s %-20s %-15s %-15s %-25s %-20s GPA:%-4.2f Grad:%-6s %-10s",
                app.getId(),
                truncate(app.getName(), 20),
                truncate(app.getLocation(), 15),
                truncate(app.getSkill(), 15),
                truncate(app.getUniversity(), 25),
                truncate(app.getMajor(), 20),
                app.getGpa(),
                app.getGraduationYear(),
                app.getApplicationStatus());
    }

    /**
     * Truncates long strings for display
     */
    private String truncate(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * Reads line input from user
     */
    private String readInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Reads and validates integer input
     */
    private int readIntInput(int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(readInput());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    /**
     * Reads and validates double input
     */
    private double readDoubleInput(double min, double max) {
        while (true) {
            try {
                double value = Double.parseDouble(readInput());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %.2f and %.2f: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    /**
     * Reads and validates double input with initial value
     */
    private double readDoubleInput(double min, double max, String initialInput) {
        if (!initialInput.isEmpty()) {
            try {
                double value = Double.parseDouble(initialInput);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Fall through to regular input handling
            }
        }
        
        System.out.printf("Please enter a number between %.2f and %.2f: ", min, max);
        return readDoubleInput(min, max);
    }

    /**
     * Reads and validates integer input with initial value
     */
    private int readIntInput(int min, int max, String initialInput) {
        if (!initialInput.isEmpty()) {
            try {
                int value = Integer.parseInt(initialInput);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Fall through to regular input handling
            }
        }
        
        System.out.printf("Please enter a number between %d and %d: ", min, max);
        return readIntInput(min, max);
    }

    /**
     * Displays a menu and gets user selection
     */
    private int displayMenu(String title, String[] options) {
        return displayMenu(title, options, false);
    }

    /**
     * Displays a menu with an optional "No change" option at the beginning
     * for update operations
     * 
     * @param title Menu title
     * @param options Array of options
     * @param includeNoChangeOption Whether to include "No change" as first option
     * @return User's selection (1-based index into options array)
     */
    private int displayMenu(String title, String[] options, boolean includeNoChangeOption) {
        System.out.println("\n" + title);
        
        int startIndex = 1;
        if (includeNoChangeOption) {
            System.out.println("1. No change");
            startIndex = 2;
        }
        
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s\n", i + startIndex, options[i]);
        }
        
        int maxChoice = options.length + (includeNoChangeOption ? 1 : 0);
        System.out.print("Enter choice (1-" + maxChoice + "): ");
        return readIntInput(1, maxChoice);
    }

    // Main UI functions

    /**
     * UI for adding a new applicant
     */
    private void addApplicant() {
        System.out.println("\nADD NEW APPLICANT");

        // Get ID from controller
        String id = applicantManagement.generateNewId();
        System.out.println("ID: " + id);

        // Collect user input
        System.out.print("Name: ");
        String name = readInput();

        int locationChoice = displayMenu("Location", LOCATIONS);
        String location = LOCATIONS[locationChoice - 1];

        int skillChoice = displayMenu("Skill", SKILLS);
        String skill = SKILLS[skillChoice - 1];

        int universityChoice = displayMenu("University", ApplicantInitializer.MALAYSIAN_UNIVERSITIES);
        String university = ApplicantInitializer.MALAYSIAN_UNIVERSITIES[universityChoice - 1];

        int majorChoice = displayMenu("Major", ApplicantInitializer.MAJORS);
        String major = ApplicantInitializer.MAJORS[majorChoice - 1];

        System.out.print("GPA (0.00-4.00): ");
        double gpa = readDoubleInput(0.00, 4.00);

        System.out.print("Graduation Year (1900-2100): ");
        String gradYear = String.valueOf(readIntInput(1900, 2100));

        // Create applicant object and add through controller
        Applicant newApplicant = new Applicant(id, name, location, skill,
                university, major, gpa, gradYear, "Applied");
        applicantManagement.addApplicant(newApplicant);

        System.out.println("\nApplicant added successfully:");
        System.out.println(formatApplicant(newApplicant));
    }

    /**
     * UI for updating an existing applicant
     */
    private void updateApplicant() {
        System.out.println("\nUPDATE APPLICANT");

        ListInterface<Applicant> allApplicants = applicantManagement.getAllApplicants();
        if (allApplicants.size() == 0) {
            System.out.println("No applicants found in the system.");
            return;
        }

        // Display applicants
        System.out.println("Current applicants:");
        for (int i = 0; i < allApplicants.size(); i++) {
            Applicant app = allApplicants.get(i);
            System.out.printf("%s: %s - %s\n", app.getId(), app.getName(), app.getSkill());
        }

        // Get ID to update
        System.out.print("\nEnter ID of applicant to update: ");
        String id = readInput();

        // Find the applicant using controller
        Applicant existingApplicant = applicantManagement.findApplicantById(id);
        if (existingApplicant == null) {
            System.out.println("Applicant with ID " + id + " not found!");
            return;
        }

        // Show current values and collect new values
        System.out.println("Updating applicant: " + existingApplicant.getName());

        System.out.print("New name [" + existingApplicant.getName() + "] (press Enter to keep current): ");
        String input = readInput();
        String name = input.isEmpty() ? existingApplicant.getName() : input;

        // For other fields, include a "No change" option
        System.out.println("Current location: " + existingApplicant.getLocation());
        int locationChoice = displayMenu("New location", LOCATIONS, true);
        
        // When a menu includes "No change" option (choice 1), we need to adjust array indices:
        // If choice 1 is selected -> keep existing value
        // If choice 2+ is selected -> map to LOCATIONS[choice-2] (subtract 2 because:
        //   1) Array is 0-indexed but menu choices start at 1
        //   2) The "No change" option takes position 1, shifting all actual options by 1)
        String location = locationChoice == 1 ? existingApplicant.getLocation() : LOCATIONS[locationChoice - 2];

        System.out.println("Current skill: " + existingApplicant.getSkill());
        int skillChoice = displayMenu("New skill", SKILLS, true);
        String skill = skillChoice == 1 ? existingApplicant.getSkill() : SKILLS[skillChoice - 2];

        System.out.println("Current university: " + existingApplicant.getUniversity());
        int universityChoice = displayMenu("New university", ApplicantInitializer.MALAYSIAN_UNIVERSITIES, true);
        String university = universityChoice == 1 ? existingApplicant.getUniversity() 
                                                : ApplicantInitializer.MALAYSIAN_UNIVERSITIES[universityChoice - 2];

        System.out.println("Current major: " + existingApplicant.getMajor());
        int majorChoice = displayMenu("New major", ApplicantInitializer.MAJORS, true);
        String major = majorChoice == 1 ? existingApplicant.getMajor() 
                                      : ApplicantInitializer.MAJORS[majorChoice - 2];

        System.out.printf("Current GPA: %.2f\n", existingApplicant.getGpa());
        System.out.print("New GPA (0.00-4.00) or press Enter to keep current: ");
        String gpaInput = readInput();
        double gpa = gpaInput.isEmpty() ? existingApplicant.getGpa() : readDoubleInput(0.00, 4.00, gpaInput);

        System.out.println("Current graduation year: " + existingApplicant.getGraduationYear());
        System.out.print("New graduation year (1900-2100) or press Enter to keep current: ");
        String gradYearInput = readInput();
        String gradYear = gradYearInput.isEmpty() ? existingApplicant.getGraduationYear() 
                                                : String.valueOf(readIntInput(1900, 2100, gradYearInput));

        System.out.println("Current status: " + existingApplicant.getApplicationStatus());
        int statusChoice = displayMenu("New status", STATUS_OPTIONS, true);
        String status = statusChoice == 1 ? existingApplicant.getApplicationStatus() 
                                        : STATUS_OPTIONS[statusChoice - 2];

        // Create updated applicant and update through controller
        Applicant updatedApplicant = new Applicant(id, name, location, skill,
                university, major, gpa, gradYear, status);

        boolean success = applicantManagement.updateApplicant(id, updatedApplicant);

        if (success) {
            System.out.println("\nApplicant updated successfully!");
            System.out.println("Updated information:");
            System.out.println(formatApplicant(updatedApplicant));
        } else {
            System.out.println("\nError: Failed to update applicant.");
        }
    }

    /**
     * UI for removing an applicant
     */
    private void removeApplicant() {
        System.out.println("\nREMOVE APPLICANT");

        ListInterface<Applicant> allApplicants = applicantManagement.getAllApplicants();
        if (allApplicants.size() == 0) {
            System.out.println("No applicants found in the system.");
            return;
        }

        // Display applicants
        System.out.println("Current applicants:");
        for (int i = 0; i < allApplicants.size(); i++) {
            Applicant app = allApplicants.get(i);
            System.out.printf("%s: %s - %s\n", app.getId(), app.getName(), app.getSkill());
        }

        // Get ID to remove
        System.out.print("\nEnter ID of applicant to remove: ");
        String id = readInput();

        // Find the applicant using controller
        Applicant applicant = applicantManagement.findApplicantById(id);
        if (applicant == null) {
            System.out.println("Applicant with ID " + id + " not found!");
            return;
        }

        // Confirm deletion
        System.out.println("About to remove:");
        System.out.println(formatApplicant(applicant));
        System.out.print("Confirm removal? (y/n): ");
        String confirm = readInput();

        if (confirm.equalsIgnoreCase("y")) {
            boolean success = applicantManagement.removeApplicant(id);
            if (success) {
                System.out.println("\nApplicant removed successfully.");
            } else {
                System.out.println("\nError: Failed to remove applicant.");
            }
        } else {
            System.out.println("\nRemoval cancelled.");
        }
    }

    /**
     * UI for filtering applicants
     */
    private void filterApplicants() {
        System.out.println("\nFILTER APPLICANTS");

        // Collect filter criteria
        String locationFilter = "";
        String skillFilter = "";
        String universityFilter = "";
        String majorFilter = "";
        double gpaFilter = 0.0;
        int startYearFilter = 0;
        int endYearFilter = 2100;
        boolean anyFilterSelected = false;

        // Get location filter
        System.out.print("Filter by location? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            int choice = displayMenu("Select location", LOCATIONS);
            locationFilter = LOCATIONS[choice - 1];
            anyFilterSelected = true;
        }

        // Get skill filter
        System.out.print("Filter by skill? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            int choice = displayMenu("Select skill", SKILLS);
            skillFilter = SKILLS[choice - 1];
            anyFilterSelected = true;
        }

        // Get university filter
        System.out.print("Filter by university? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            int choice = displayMenu("Select university", ApplicantInitializer.MALAYSIAN_UNIVERSITIES);
            universityFilter = ApplicantInitializer.MALAYSIAN_UNIVERSITIES[choice - 1];
            anyFilterSelected = true;
        }

        // Get major filter
        System.out.print("Filter by major? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            int choice = displayMenu("Select major", ApplicantInitializer.MAJORS);
            majorFilter = ApplicantInitializer.MAJORS[choice - 1];
            anyFilterSelected = true;
        }

        // Get GPA filter
        System.out.print("Filter by minimum GPA? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            System.out.print("Minimum GPA (0.00-4.00): ");
            gpaFilter = readDoubleInput(0.00, 4.00);
            anyFilterSelected = true;
        }

        // Get graduation year range filter
        System.out.print("Filter by graduation year range? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            System.out.print("Start year (1900-2100): ");
            startYearFilter = readIntInput(1900, 2100);
            System.out.print("End year (>= start year): ");
            endYearFilter = readIntInput(startYearFilter, 2100);
            anyFilterSelected = true;
        }

        // Check if no filters were selected
        if (!anyFilterSelected) {
            System.out.println("\nNo filters were selected. Showing all applicants.");
            displayAllApplicants();
            return;
        }

        // Print selected filters
        System.out.println("\nActive filters:");
        if (!locationFilter.isEmpty())
            System.out.println("Location: " + locationFilter);
        if (!skillFilter.isEmpty())
            System.out.println("Skill: " + skillFilter);
        if (!universityFilter.isEmpty())
            System.out.println("University: " + universityFilter);
        if (!majorFilter.isEmpty())
            System.out.println("Major: " + majorFilter);
        if (gpaFilter > 0)
            System.out.printf("Minimum GPA: %.2f\n", gpaFilter);
        if (startYearFilter > 0)
            System.out.printf("Graduation year: %d-%d\n", startYearFilter, endYearFilter);

        // Create final variables for use in lambda
        final String fLocation = locationFilter;
        final String fSkill = skillFilter;
        final String fUniversity = universityFilter;
        final String fMajor = majorFilter;
        final double fGpa = gpaFilter;
        final int fStartYear = startYearFilter;
        final int fEndYear = endYearFilter;

        // Create filter criteria
        FilterCriteria criteria = applicant -> {
            if (!fLocation.isEmpty() && !applicant.getLocation().equalsIgnoreCase(fLocation))
                return false;
            if (!fSkill.isEmpty() && !applicant.getSkill().equalsIgnoreCase(fSkill))
                return false;
            if (!fUniversity.isEmpty() && !applicant.getUniversity().equalsIgnoreCase(fUniversity))
                return false;
            if (!fMajor.isEmpty() && !applicant.getMajor().equalsIgnoreCase(fMajor))
                return false;
            if (fGpa > 0 && applicant.getGpa() < fGpa)
                return false;
            if (fStartYear > 0) {
                try {
                    int gradYear = Integer.parseInt(applicant.getGraduationYear());
                    if (gradYear < fStartYear || gradYear > fEndYear)
                        return false;
                } catch (NumberFormatException e) {
                    return false; // Invalid graduation year format
                }
            }
            return true;
        };

        // Apply filter through controller
        ListInterface<Applicant> filteredApplicants = applicantManagement.filterApplicants(criteria);

        // Display results
        System.out.println("\nFILTER RESULTS:");
        if (filteredApplicants.size() > 0) {
            for (int i = 0; i < filteredApplicants.size(); i++) {
                System.out.println(formatApplicant(filteredApplicants.get(i)));
            }
            System.out.println("\nTotal matches: " + filteredApplicants.size());
        } else {
            System.out.println("No matches found.");
        }
    }

    /**
     * UI for displaying all applicants
     */
    private void displayAllApplicants() {
        System.out.println("\nALL APPLICANTS");

        ListInterface<Applicant> allApplicants = applicantManagement.getAllApplicants();

        if (allApplicants.size() > 0) {
            // Calculate statistics
            int totalApplicants = allApplicants.size();
            double avgGpa = 0;
            int appliedCount = 0, pendingCount = 0, interviewCount = 0, offeredCount = 0;

            for (int i = 0; i < totalApplicants; i++) {
                Applicant app = allApplicants.get(i);
                avgGpa += app.getGpa();

                switch (app.getApplicationStatus().toLowerCase()) {
                    case "applied":
                        appliedCount++;
                        break;
                    case "pending":
                        pendingCount++;
                        break;
                    case "interview":
                        interviewCount++;
                        break;
                    case "offered":
                        offeredCount++;
                        break;
                }
            }
            avgGpa /= totalApplicants;

            // Display statistics
            System.out.println("\nAPPLICANT STATISTICS:");
            System.out.printf("Total Applicants: %d\n", totalApplicants);
            System.out.printf("Average GPA: %.2f\n", avgGpa);
            System.out.printf("Status Breakdown: Applied: %d | Pending: %d | Interview: %d | Offered: %d\n",
                    appliedCount, pendingCount, interviewCount, offeredCount);

            // Display applicant list
            System.out.println("\nAPPLICANT LIST:");
            for (int i = 0; i < allApplicants.size(); i++) {
                System.out.println(formatApplicant(allApplicants.get(i)));
            }
        } else {
            System.out.println("No applicants found in the system.");
        }
    }
}
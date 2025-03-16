package com.mycompany.internshipsystemy2s3.boundary;

import com.mycompany.internshipsystemy2s3.control.JobControllerInterface;
import com.mycompany.internshipsystemy2s3.entity.Job;
import com.mycompany.internshipsystemy2s3.dao.JobDAOInterface.FilterCriteria;
import com.mycompany.internshipsystemy2s3.dao.JobInitializer;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * User interface for the job management system.
 * Handles presentation logic and user interaction.
 */
public class JobManagementUI {
    private JobControllerInterface jobManagement;
    private Scanner scanner;

    // UI selection arrays - moved from DAO to UI where they belong
    private static final String[] LOCATIONS = JobInitializer.LOCATIONS;
    private static final String[] SKILLS = JobInitializer.SKILLS;

    // Salary Ranges for filtering
    private static final String[] SALARY_RANGES = {
            "Below RM3000",
            "RM3000 - RM5000",
            "RM5000 - RM7000",
            "RM7000 - RM10000",
            "Above RM10000"
    };


    /**
     * Constructor that accepts controller reference
     * @param controller The controller this UI will use
     */
    public JobManagementUI(JobControllerInterface controller) {
        this.jobManagement = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Default constructor for when no controller is provided yet
     */
    public JobManagementUI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Sets the controller for this UI
     * @param controller The controller to use
     */
    public void setController(JobControllerInterface controller) {
        this.jobManagement = controller;
    }

    /**
     * Displays the main menu and handles user navigation
     */
    public void displayMainMenu() {
        String[] mainOptions = {
                "Add New Job Posting",
                "Update Existing Job Posting",
                "Remove Job Posting",
                "Filter Job Postings",
                "Display All Job Postings",
                "Generate Report", // Added Generate Report
                "Exit\n"             // Exit is now option 7
        };

        boolean running = true;
        while (running) {
        System.out.println("\n===============================================================");
        System.out.println("               INTERNSHIP JOB MANAGEMENT SYSTEM                ");
        System.out.println("===============================================================");                           
        int choice = displayMenu("MAIN MENU\n", mainOptions);
        
            switch (choice) {
                case 1:
                    addJobPosting();
                    break;
                case 2:
                    updateJobPosting();
                    break;
                case 3:
                    removeJobPosting();
                    break;
                case 4:
                    filterJobPostings();
                    break;
                case 5:
                    displayAllJobPostings();
                    break;
                case 6:
                    generateReport(); // Case for Generate Report
                    break;
                case 7: // Exit is now case 7
                    System.out.println("\nExiting Job Management System. Goodbye!");
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
     * Formats a job for display
     */
    private String formatJob(Job job) {
        return String.format("%-6s %-30s %-20s %-15s %-10s %-20s %-40s", // Added description in format
                job.getJobId(),
                truncate(job.getTitle(), 30),
                truncate(job.getCompany(), 20),
                truncate(job.getLocation(), 15),
                "RM" + String.format("%.2f", job.getSalaryAmount()), // Display salary with RM and format
                truncate(job.getPostingDate(), 20),
                truncate(job.getDescription(), 40)); // Display description
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
     * Reads and validates double input for salary
     */
    private double readSalaryInput() {
        while (true) {
            try {
                double value = scanner.nextDouble();
                if (value >= 0) { // Assuming salary cannot be negative
                    scanner.nextLine(); // consume newline left-over
                    return value;
                }
                System.out.print("Salary must be a non-negative number. Enter salary: ");
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number for salary: ");
                scanner.nextLine(); // discard non-number input
            }
        }
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
     * UI for adding a new job posting
     */
    private void addJobPosting() {
        System.out.println("\nADD NEW JOB POSTING");

        // Get ID from controller
        String jobId = jobManagement.generateNewId();
        System.out.println("Job ID: " + jobId);

        // Collect user input
        System.out.print("Job Title: ");
        String title = readInput();

        System.out.print("Job Description: ");
        String description = readInput();

        System.out.print("Company Name: ");
        String company = readInput();

        int locationChoice = displayMenu("Location", LOCATIONS);
        String location = LOCATIONS[locationChoice - 1];

        System.out.print("Salary Amount (Numbers only): RM"); // Prompt for numeric salary
        double salaryAmount = readSalaryInput(); // Read numeric salary

        System.out.print("Skills (comma-separated): ");
        String skill = SKILLS[displayMenu("Skills", SKILLS) - 1];

        System.out.print("Posting Date (YYYY-MM-DD): ");
        String postingDate = readInput();

        // Create job object and add through controller
        Job newJob = new Job(jobId, title, description, company, location, salaryAmount, skill, postingDate);
        jobManagement.addJob(newJob);

        System.out.println("\nJob posting added successfully:");
        System.out.println(formatJob(newJob)); // Format job now includes description
    }

    /**
     * UI for updating an existing job posting
     */
    private void updateJobPosting() {
        System.out.println("\nUPDATE JOB POSTING");

        ListInterface<Job> allJobs = jobManagement.getAllJobs();
        if (allJobs.size() == 0) {
            System.out.println("No job postings found in the system.");
            return;
        }

        // Display jobs
        System.out.println("Current job postings:");
        for (int i = 0; i < allJobs.size(); i++) {
            Job job = allJobs.get(i);
            System.out.printf("%s: %s - %s\n", job.getJobId(), job.getTitle(), job.getCompany());
        }

        // Get ID to update
        System.out.print("\nEnter ID of job posting to update: ");
        String jobId = readInput();

        // Find the job using controller
        Job existingJob = jobManagement.findJobById(jobId);
        if (existingJob == null) {
            System.out.println("Job posting with ID " + jobId + " not found!");
            return;
        }

        // Show current values and collect new values
        System.out.println("Updating job posting: " + existingJob.getTitle());

        System.out.print("New job title [" + existingJob.getTitle() + "] (press Enter to keep current): ");
        String input = readInput();
        String title = input.isEmpty() ? existingJob.getTitle() : input;

        System.out.println("Current description: " + existingJob.getDescription());
        System.out.print("New description [" + existingJob.getDescription() + "] (press Enter to keep current): ");
        input = readInput();
        String description = input.isEmpty() ? existingJob.getDescription() : input;

        System.out.println("Current company: " + existingJob.getCompany());
        System.out.print("New company name [" + existingJob.getCompany() + "] (press Enter to keep current): ");
        input = readInput();
        String company = input.isEmpty() ? existingJob.getCompany() : input;


        System.out.println("Current location: " + existingJob.getLocation());
        int locationChoice = displayMenu("New location", LOCATIONS, true);
        String location = locationChoice == 1 ? existingJob.getLocation() : LOCATIONS[locationChoice - 2];

        System.out.printf("Current salary: RM%.2f\n", existingJob.getSalaryAmount());
        System.out.printf("New salary amount [RM%.2f] (Enter number only, press Enter to keep current): RM", existingJob.getSalaryAmount());
        String salaryInput = readInput();
        double salaryAmount = salaryInput.isEmpty() ? existingJob.getSalaryAmount() : Double.parseDouble(salaryInput);


        System.out.println("Current skills: " + existingJob.getSkills());
        int skillChoice = displayMenu("New skills", SKILLS, true);
        String skill = skillChoice == 1 ? existingJob.getSkills() : SKILLS[skillChoice - 2];

        System.out.println("Current posting date: " + existingJob.getPostingDate());
        System.out.print("New posting date (YYYY-MM-DD) [" + existingJob.getPostingDate() + "] (press Enter to keep current): ");
        input = readInput();
        String postingDate = input.isEmpty() ? existingJob.getPostingDate() : input;


        // Create updated job and update through controller
        Job updatedJob = new Job(jobId, title, description, company, location, salaryAmount, skill, postingDate);

        boolean success = jobManagement.updateJob(jobId, updatedJob);

        if (success) {
            System.out.println("\nJob posting updated successfully!");
            System.out.println("Updated information:");
            System.out.println(formatJob(updatedJob)); // Format job now includes description
        } else {
            System.out.println("\nError: Failed to update job posting.");
        }
    }

    /**
     * UI for removing a job posting
     */
    private void removeJobPosting() {
        // ... (No changes needed here) ...
        System.out.println("\nREMOVE JOB POSTING");

        ListInterface<Job> allJobs = jobManagement.getAllJobs();
        if (allJobs.size() == 0) {
            System.out.println("No job postings found in the system.");
            return;
        }

        // Display jobs
        System.out.println("Current job postings:");
        for (int i = 0; i < allJobs.size(); i++) {
            Job job = allJobs.get(i);
            System.out.printf("%s: %s - %s\n", job.getJobId(), job.getTitle(), job.getCompany());
        }

        // Get ID to remove
        System.out.print("\nEnter ID of job posting to remove: ");
        String jobId = readInput();

        // Find the job using controller
        Job job = jobManagement.findJobById(jobId);
        if (job == null) {
            System.out.println("Job posting with ID " + jobId + " not found!");
            return;
        }

        // Confirm deletion
        System.out.println("About to remove:");
        System.out.println(formatJob(job));
        System.out.print("Confirm removal? (y/n): ");
        String confirm = readInput();

        if (confirm.equalsIgnoreCase("y")) {
            boolean success = jobManagement.removeJob(jobId);
            if (success) {
                System.out.println("\nJob posting removed successfully.");
            } else {
                System.out.println("\nError: Failed to remove job posting.");
            }
        } else {
            System.out.println("\nRemoval cancelled.");
        }
    }

    /**
     * UI for filtering job postings
     */
    private void filterJobPostings() {
        System.out.println("\nFILTER JOB POSTINGS");

        // Collect filter criteria
        String locationFilter = "";
        String companyFilter = "";
        String skillsFilter = "";
        String salaryRangeFilter = ""; // Will be string from SALARY_RANGES
        double minSalary = -1, maxSalary = -1; // Numerical salary range values
        boolean anyFilterSelected = false;


        // Get location filter
        System.out.print("Filter by location? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            int choice = displayMenu("Select location", LOCATIONS);
            locationFilter = LOCATIONS[choice - 1];
            anyFilterSelected = true;
        }

        // Get company filter
        System.out.print("Filter by company name? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            System.out.print("Enter company name to filter by: ");
            companyFilter = readInput();
            anyFilterSelected = true;
        }

        // Get skills filter
        System.out.print("Filter by skills? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            int choice = displayMenu("Select skill", SKILLS);
            skillsFilter = SKILLS[choice - 1];
            anyFilterSelected = true;
        }

        // Get salary range filter
        System.out.print("Filter by salary range? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            int choice = displayMenu("Select salary range", SALARY_RANGES);
            salaryRangeFilter = SALARY_RANGES[choice - 1];
            anyFilterSelected = true;

            // Convert selected salary range string to numerical min/max values
            switch (salaryRangeFilter) {
                case "Below RM3000": minSalary = 0; maxSalary = 3000; break;
                case "RM3000 - RM5000": minSalary = 3000; maxSalary = 5000; break;
                case "RM5000 - RM7000": minSalary = 5000; maxSalary = 7000; break;
                case "RM7000 - RM10000": minSalary = 7000; maxSalary = 10000; break;
                case "Above RM10000": minSalary = 10000; maxSalary = Double.MAX_VALUE; break;
            }
        }


        // Check if no filters were selected
        if (!anyFilterSelected) {
            System.out.println("\nNo filters were selected. Showing all job postings.");
            displayAllJobPostings();
            return;
        }

        // Print selected filters
        System.out.println("\nActive filters:");
        if (!locationFilter.isEmpty())
            System.out.println("Location: " + locationFilter);
        if (!companyFilter.isEmpty())
            System.out.println("Company: " + companyFilter);
        if (!skillsFilter.isEmpty())
            System.out.println("Skills: " + skillsFilter);
        if (!salaryRangeFilter.isEmpty())
            System.out.println("Salary Range: " + salaryRangeFilter);


        // Create final variables for use in lambda
        final String fLocation = locationFilter;
        final String fCompany = companyFilter;
        final String fSkills = skillsFilter;
        final double fMinSalary = minSalary;
        final double fMaxSalary = maxSalary;


        // Create filter criteria
        FilterCriteria criteria = job -> {
            if (!fLocation.isEmpty() && !job.getLocation().equalsIgnoreCase(fLocation))
                return false;
            if (!fCompany.isEmpty() && !job.getCompany().toLowerCase().contains(fCompany.toLowerCase()))
                return false;
            if (!fSkills.isEmpty() && !job.getSkills().toLowerCase().contains(fSkills.toLowerCase()))
                return false;
            if (fMinSalary != -1) { // Salary filter is active
                if (job.getSalaryAmount() < fMinSalary || job.getSalaryAmount() >= fMaxSalary) // Range check
                    return false;
            }
            return true;
        };

        // Apply filter through controller
        ListInterface<Job> filteredJobs = jobManagement.filterJobs(criteria);

        // Display results
        System.out.println("\nFILTER RESULTS:");
        if (filteredJobs.size() > 0) {
            System.out.println("\n"+String.format("%-6s %-30s %-20s %-15s %-10s %-20s %-40s", "ID", "Title", "Company", "Location", "Salary", "Post Date", "Description"));
            for (int i = 0; i < filteredJobs.size(); i++) {
                System.out.println(formatJob(filteredJobs.get(i))); // Format job now includes description
            }
            System.out.println("\nTotal matches: " + filteredJobs.size());
        } else {
            System.out.println("No matches found.");
        }
    }


    /**
     * UI for displaying all job postings
     */
    private void displayAllJobPostings() {
        System.out.println("\nALL JOB POSTINGS");

        ListInterface<Job> allJobs = jobManagement.getAllJobs();

        if (allJobs.size() > 0) {
            System.out.println("\n"+String.format("%-6s %-30s %-20s %-15s %-10s %-20s %-40s", "ID", "Title", "Company", "Location", "Salary", "Post Date", "Description"));
            for (int i = 0; i < allJobs.size(); i++) {
                System.out.println(formatJob(allJobs.get(i))); // Format job now includes description
            }
        } else {
            System.out.println("No job postings found in the system.");
        }
    }

    /**
     * UI for generating a report
     */
    private void generateReport() {
        System.out.println("\nJOB POSTINGS REPORT");
        ListInterface<Job> allJobs = jobManagement.getAllJobs();
        int totalJobs = allJobs.size();

        System.out.println("--- Job Postings Summary ---");
        System.out.println("Total Job Postings: " + totalJobs);
        System.out.println("--- End of Report ---");
    }
}
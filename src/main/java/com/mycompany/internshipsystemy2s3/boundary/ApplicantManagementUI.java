package com.mycompany.internshipsystemy2s3.boundary;

import com.mycompany.internshipsystemy2s3.control.ApplicantManagement;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.dao.ApplicantDAO;
import com.mycompany.internshipsystemy2s3.dao.ApplicantDAO.FilterCriteria;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;
import java.util.Scanner;

public class ApplicantManagementUI {
    private ApplicantManagement applicantManagement;
    private Scanner scanner;

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

    public ApplicantManagementUI() {
        this.applicantManagement = new ApplicantManagement();
        this.scanner = new Scanner(System.in);
    }

    private String formatApplicant(Applicant app) {
        return String.format("%-8s %-20s %-15s %-15s %-25s %-20s GPA:%-5.2f Grad:%-6s %-10s", 
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

    // Helper method to truncate strings that are too long
    private String truncate(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    private String readInput() {
        return scanner.nextLine().trim();
    }

    private int readIntInput(int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(readInput());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Enter number %d-%d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid. Enter number: ");
            }
        }
    }

    private double readDoubleInput(double min, double max) {
        while (true) {
            try {
                double value = Double.parseDouble(readInput());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Enter number %.2f-%.2f: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid. Enter number: ");
            }
        }
    }

    private int displayMenu(String title, String[] options) {
        System.out.println("\n" + title);
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s\n", i + 1, options[i]);
        }
        System.out.print("Choose: ");
        return readIntInput(1, options.length);
    }

    public void displayMainMenu() {
        String[] menuOptions = {
            "Add New Applicant", 
            "Update Existing Applicant", 
            "Remove Applicant", 
            "Filter Applicants", 
            "Display All Applicants", 
            "Exit"
        };
        
        while (true) {
            System.out.println("\nINTERNSHIP APPLICANT MANAGEMENT SYSTEM");
            int choice = displayMenu("MENU", menuOptions);

            switch (choice) {
                case 1: addApplicant(); break;
                case 2: updateApplicant(); break;
                case 3: removeApplicant(); break;
                case 4: filterApplicants(); break;
                case 5: displayAllApplicants(); break;
                case 6: 
                    System.out.println("Goodbye!");
                    return;
            }
            
            System.out.print("Press Enter to continue...");
            readInput();
        }
    }

    private void addApplicant() {
        System.out.println("\nADD NEW APPLICANT");
        
        String id = applicantManagement.generateNewId();
        System.out.println("ID: " + id);
        
        System.out.print("Name: ");
        String name = readInput();

        int locationChoice = displayMenu("Location", LOCATIONS);
        String location = LOCATIONS[locationChoice-1];

        int skillChoice = displayMenu("Skill", SKILLS);
        String skill = SKILLS[skillChoice-1];

        int universityChoice = displayMenu("University", ApplicantDAO.MALAYSIAN_UNIVERSITIES);
        String university = ApplicantDAO.MALAYSIAN_UNIVERSITIES[universityChoice-1];
        
        int majorChoice = displayMenu("Major", ApplicantDAO.MAJORS);
        String major = ApplicantDAO.MAJORS[majorChoice-1];
        
        System.out.print("GPA (0.00-4.00): ");
        double gpa = readDoubleInput(0.00, 4.00);
        
        System.out.print("Graduation Year (1900-2100): ");
        String gradYear = String.valueOf(readIntInput(1900, 2100));
        
        Applicant applicant = new Applicant(id, name, location, skill,
                                          university, major, gpa, gradYear, "Applied");
        applicantManagement.addApplicant(applicant);
        
        System.out.println("\nAdded: " + formatApplicant(applicant));
    }

    private void updateApplicant() {
        System.out.println("\nUPDATE APPLICANT");
        
        ListInterface<Applicant> allApplicants = applicantManagement.getAllApplicants();
        if (allApplicants.size() == 0) {
            System.out.println("No applicants found.");
            return;
        }
        
        System.out.println("Current applicants:");
        for (int i = 0; i < allApplicants.size(); i++) {
            Applicant app = allApplicants.get(i);
            System.out.printf("%s: %s - %s\n", app.getId(), app.getName(), app.getSkill());
        }
        
        System.out.print("\nID to update: ");
        String id = readInput();
        
        boolean idExists = false;
        for (int i = 0; i < allApplicants.size(); i++) {
            if (allApplicants.get(i).getId().equals(id)) {
                idExists = true;
                break;
            }
        }
        
        if (!idExists) {
            System.out.println("ID not found!");
            return;
        }

        System.out.print("New Name: ");
        String name = readInput();

        int locationChoice = displayMenu("New Location", LOCATIONS);
        String location = LOCATIONS[locationChoice-1];

        int skillChoice = displayMenu("New Skill", SKILLS);
        String skill = SKILLS[skillChoice-1];

        int universityChoice = displayMenu("New University", ApplicantDAO.MALAYSIAN_UNIVERSITIES);
        String university = ApplicantDAO.MALAYSIAN_UNIVERSITIES[universityChoice-1];
        
        int majorChoice = displayMenu("New Major", ApplicantDAO.MAJORS);
        String major = ApplicantDAO.MAJORS[majorChoice-1];
        
        System.out.print("New GPA (0.00-4.00): ");
        double gpa = readDoubleInput(0.00, 4.00);
        
        System.out.print("New Graduation Year (1900-2100): ");
        String gradYear = String.valueOf(readIntInput(1900, 2100));
        
        int statusChoice = displayMenu("New Status", STATUS_OPTIONS);
        String status = STATUS_OPTIONS[statusChoice-1];
        
        Applicant updatedApplicant = new Applicant(id, name, location, skill,
                                                 university, major, gpa, gradYear, status);
        applicantManagement.updateApplicant(id, updatedApplicant);
        
        System.out.println("\nUpdated: " + formatApplicant(updatedApplicant));
    }

    private void removeApplicant() {
        System.out.println("\nREMOVE APPLICANT");
        
        ListInterface<Applicant> allApplicants = applicantManagement.getAllApplicants();
        if (allApplicants.size() == 0) {
            System.out.println("No applicants found.");
            return;
        }
        
        System.out.println("Current applicants:");
        for (int i = 0; i < allApplicants.size(); i++) {
            Applicant app = allApplicants.get(i);
            System.out.printf("%s: %s - %s\n", app.getId(), app.getName(), app.getSkill());
        }
        
        System.out.print("\nID to remove: ");
        String id = readInput();
        
        boolean idExists = false;
        for (int i = 0; i < allApplicants.size(); i++) {
            if (allApplicants.get(i).getId().equals(id)) {
                idExists = true;
                break;
            }
        }
        
        if (!idExists) {
            System.out.println("ID not found!");
            return;
        }
        
        System.out.print("Confirm deletion (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            applicantManagement.removeApplicant(id);
            System.out.println("Applicant removed.");
        } else {
            System.out.println("Cancelled.");
        }
    }

    private void filterApplicants() {
        System.out.println("\nFILTER APPLICANTS");
        
        String locationFilter = "";
        System.out.print("Filter by location? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            locationFilter = LOCATIONS[displayMenu("Location", LOCATIONS) - 1];
        }
        
        String skillFilter = "";
        System.out.print("Filter by skill? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            skillFilter = SKILLS[displayMenu("Skill", SKILLS) - 1];
        }
        
        String universityFilter = "";
        System.out.print("Filter by university? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            universityFilter = ApplicantDAO.MALAYSIAN_UNIVERSITIES[
                displayMenu("University", ApplicantDAO.MALAYSIAN_UNIVERSITIES) - 1];
        }
        
        String majorFilter = "";
        System.out.print("Filter by major? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            majorFilter = ApplicantDAO.MAJORS[displayMenu("Major", ApplicantDAO.MAJORS) - 1];
        }
        
        double gpaFilter = 0.0;
        System.out.print("Filter by minimum GPA? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            System.out.print("Minimum GPA (0.00-4.00): ");
            gpaFilter = readDoubleInput(0.00, 4.00);
        }
        
        int startYearFilter = 0;
        int endYearFilter = 2100;
        System.out.print("Filter by graduation year? (y/n): ");
        if (readInput().equalsIgnoreCase("y")) {
            System.out.print("Start year (1900-2100): ");
            startYearFilter = readIntInput(1900, 2100);
            System.out.print("End year (>= start year): ");
            endYearFilter = readIntInput(startYearFilter, 2100);
        }
        
        // Print selected filters
        System.out.println("\nFilters:");
        if (!locationFilter.isEmpty()) System.out.println("Location: " + locationFilter);
        if (!skillFilter.isEmpty()) System.out.println("Skill: " + skillFilter);
        if (!universityFilter.isEmpty()) System.out.println("University: " + universityFilter);
        if (!majorFilter.isEmpty()) System.out.println("Major: " + majorFilter);
        if (gpaFilter > 0) System.out.println("Min GPA: " + gpaFilter);
        if (startYearFilter > 0) System.out.println("Years: " + startYearFilter + "-" + endYearFilter);

        final String fLocation = locationFilter;
        final String fSkill = skillFilter;
        final String fUniversity = universityFilter;
        final String fMajor = majorFilter;
        final double fGpa = gpaFilter;
        final int fStartYear = startYearFilter;
        final int fEndYear = endYearFilter;

        FilterCriteria criteria = applicant -> {
            if (!fLocation.isEmpty() && !applicant.getLocation().equalsIgnoreCase(fLocation)) return false;
            if (!fSkill.isEmpty() && !applicant.getSkill().equalsIgnoreCase(fSkill)) return false;
            if (!fUniversity.isEmpty() && !applicant.getUniversity().equalsIgnoreCase(fUniversity)) return false;
            if (!fMajor.isEmpty() && !applicant.getMajor().equalsIgnoreCase(fMajor)) return false;
            if (fGpa > 0 && applicant.getGpa() < fGpa) return false;
            if (fStartYear > 0) {
                int gradYear = Integer.parseInt(applicant.getGraduationYear());
                if (gradYear < fStartYear || gradYear > fEndYear) return false;
            }
            return true;
        };

        ListInterface<Applicant> filteredApplicants = applicantManagement.filterApplicants(criteria);

        System.out.println("\nRESULTS:");
        if (filteredApplicants.size() > 0) {
            for (int i = 0; i < filteredApplicants.size(); i++) {
                System.out.println(formatApplicant(filteredApplicants.get(i)));
            }
            System.out.println("Total matches: " + filteredApplicants.size());
        } else {
            System.out.println("No matches found.");
        }
    }

    private void displayAllApplicants() {
        System.out.println("\nALL APPLICANTS");
        
        ListInterface<Applicant> allApplicants = applicantManagement.getAllApplicants();
        
        if (allApplicants.size() > 0) {
            // Display statistics
            int totalApplicants = allApplicants.size();
            double avgGpa = 0;
            int appliedCount = 0, pendingCount = 0, interviewCount = 0, offeredCount = 0;
            
            for (int i = 0; i < totalApplicants; i++) {
                Applicant app = allApplicants.get(i);
                avgGpa += app.getGpa();
                
                switch (app.getApplicationStatus().toLowerCase()) {
                    case "applied": appliedCount++; break;
                    case "pending": pendingCount++; break;
                    case "interview": interviewCount++; break;
                    case "offered": offeredCount++; break;
                }
            }
            avgGpa /= totalApplicants;
            
            System.out.println("\nSTATISTICS:");
            System.out.printf("Count: %d | Avg GPA: %.2f\n", totalApplicants, avgGpa);
            System.out.printf("Applied: %d | Pending: %d | Interview: %d | Offered: %d\n", 
                appliedCount, pendingCount, interviewCount, offeredCount);
            
            // Display all applicants
            System.out.println("\nAPPLICANTS:");
            for (int i = 0; i < allApplicants.size(); i++) {
                System.out.println(formatApplicant(allApplicants.get(i)));
            }
        } else {
            System.out.println("No applicants found.");
        }
    }

    public static void main(String[] args) {
        ApplicantManagementUI ui = new ApplicantManagementUI();
        ui.displayMainMenu();
    }
}
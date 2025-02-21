## File Structure Sample

### Key Terms: Code Building Blocks
1.  **Package**: Code folders. *Organizes classes.*
2.  **Interface**: Method contract. *Defines "what" classes do.*
3.  **Entity**: Data object. *Holds application information.*
4.  **Control**: Logic & actions. *Implements application rules.*
5.  **Boundary**: User interaction. *Handles input/output.*


```
src/main/java/              (Java Code Root - Packages start here)
├── main/                   (Standard Package Folder)
│   └── java/               (Standard Folder - Folders=Package Names)

│       ├── adt/                 (Package: `main.java.adt` -  [Abstract Data Types])
│       │   ├── ArrayList.java           (Your ArrayList -  <Implementation Details>)
│       │   └── ListInterface.java       (List ADT Interface -  <Functionality Definition>)

│       ├── applicantmanagement/ (Package: `main.java.applicantmanagement` -  [Applicant Module]) @ Bryan
│       │   ├── Applicant.java           (Entity: `Applicant` Data)
│       │   ├── ApplicantController.java (Control: `Applicant` Logic)
│       │   └── ApplicantUI.java         (Boundary: `Applicant` UI)

│       ├── jobmanagement/       (Package: `main.java.jobmanagement` -  [Job Module])             @ Alia
│       │   ├── Job.java                 (Entity: `Job` Data)
│       │   ├── JobController.java       (Control: `Job` Logic)
│       │   └── JobUI.java               (Boundary: `Job` UI)

│       ├── matchingengine/      (Package: `main.java.matchingengine` -  [Matching Module])
│       │   └── MatchingService.java     (Control: Matching Logic)

│       ├── reporting/           (Package: `main.java.reporting` -  [Reporting Module])
│       │   └── ReportGenerator.java     (Control: Report Logic)

│       ├── scheduleinterview/   (Package: `main.java.scheduleinterview` -  [Scheduling Module])
│       │   └── InterviewScheduler.java  (Control: Interview Logic)

│       ├── search/              (Package: `main.java.search` -  [Search Module])
│       │   └── SearchService.java       (Control: Search Logic)

│       └── Main.java            (Program Start Point)
```


# Functional Requirements for Job & Applicant Management System

This document outlines the functional requirements for a Job and Applicant Management System, broken down into three modules: Job Management (for Employers), Applicant Management (for Job Seekers), and a Reporting Module (accessible to all relevant members).

## 1. Job Management (Employers) Module

*   **Create Job Posting:**  Functionality for employers to create new job postings with relevant details.
*   **Update Job Posting:**  Allows modification of existing job posting information.
*   **Remove Job Posting:**  Enables employers to delete job postings from the system.
*   **Filter Job Postings:**  Provides filtering of job postings based on specified criteria.

## 2. Applicant Management (Job seekers) Module

*   **Create Applicant Profile:**  Functionality for job seekers to create and save their profiles.
*   **Update Applicant Profile:**  Allows job seekers to modify their profile information.
*   **Remove Applicant Profile:**  Enables job seekers to delete their profiles from the system.
*   **Filter Applicants:**  Provides filtering of applicant profiles based on criteria (primarily for employers/admins).

## 3. Matching Engine Module

*   **Match Applicants to Jobs:**  Automatically matches applicants to relevant job postings using a defined algorithm.
*   **Generate Match Scores:**  Provides a score indicating the strength of the match between applicants and jobs.

## 4. Arrange Interview & Schedule Module

*   **Schedule Interviews:**  Functionality to schedule interviews for applicants who are matched to jobs.
*   **Filter Successful Applicants:**  Allows filtering and ranking of applicants considered successful for recruitment.

## 5. Reporting Module

*   **Job Posting Reports:**  Generates reports related to job postings, with filtering options.
*   **Applicant Reports:**  Generates reports on applicant data, with filtering options.
*   **Match Reports:**  Creates reports on the matches between jobs and applicants, including match scores.
*   **Interview & Successful Candidate Reports:**  Generates reports on interview schedules and identified successful candidates.

## 6. Search Module

*   **Keyword Search:**  Implements keyword-based searching across relevant fields.
*   **Fuzzy Matching:**  Supports fuzzy matching to find results even with slight variations in keywords.
*   **Rank Search Results:**  Ranks search results based on relevance or other defined criteria.

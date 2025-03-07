package com.mycompany.internshipsystemy2s3.dao;

import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;
import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedListInterface;
import com.mycompany.internshipsystemy2s3.entity.Applicant;
import com.mycompany.internshipsystemy2s3.entity.Location;
import com.mycompany.internshipsystemy2s3.entity.JobType;
import com.mycompany.internshipsystemy2s3.entity.Skill;

public class ApplicantDAO {
    private DoublyLinkedListInterface<Applicant> applicants;

    public ApplicantDAO() {
        this.applicants = new DoublyLinkedList<>();
        prefillData();
    }

    private void prefillData() {
        applicants.add(new Applicant("ID1", "Alice Smith", Location.NEW_YORK, JobType.FULL_TIME, Skill.DEVOPS));
        applicants.add(new Applicant("ID2", "Bob Johnson", Location.LOS_ANGELES, JobType.PART_TIME, Skill.FRONTEND));
        applicants.add(new Applicant("ID3", "Charlie Brown", Location.CHICAGO, JobType.INTERNSHIP, Skill.BACKEND));
        applicants.add(new Applicant("ID4", "David Wilson", Location.HOUSTON, JobType.FULL_TIME, Skill.FULLSTACK));
        applicants.add(new Applicant("ID5", "Eve Davis", Location.PHOENIX, JobType.PART_TIME, Skill.MOBILE));
        applicants.add(new Applicant("ID6", "Frank Miller", Location.PHILADELPHIA, JobType.INTERNSHIP, Skill.DATA_SCIENTIST));
        applicants.add(new Applicant("ID7", "Grace Lee", Location.SAN_ANTONIO, JobType.FULL_TIME, Skill.MACHINE_LEARNING_ENGINEER));
        applicants.add(new Applicant("ID8", "Hank Martin", Location.SAN_DIEGO, JobType.PART_TIME, Skill.CLOUD_ENGINEER));
        applicants.add(new Applicant("ID9", "Ivy Clark", Location.DALLAS, JobType.INTERNSHIP, Skill.SECURITY_ENGINEER));
        applicants.add(new Applicant("ID10", "Jack Lewis", Location.SAN_JOSE, JobType.FULL_TIME, Skill.DATABASE_ADMINISTRATOR));
        // Add more applicants as needed
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
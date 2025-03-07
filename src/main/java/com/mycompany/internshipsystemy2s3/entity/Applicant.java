package com.mycompany.internshipsystemy2s3.entity;

import java.util.Objects;

public class Applicant {
    private String id;
    private String name;
    private Location location;
    private JobType desiredJobType;
    private Skill skill;

    // Constructor
    public Applicant(String id, String name, Location location, JobType desiredJobType, Skill skill) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.desiredJobType = desiredJobType;
        this.skill = skill;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public JobType getDesiredJobType() {
        return desiredJobType;
    }

    public void setDesiredJobType(JobType desiredJobType) {
        this.desiredJobType = desiredJobType;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Applicant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", desiredJobType=" + desiredJobType +
                ", skill=" + skill +
                '}';
    }

    // Override equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(id, applicant.id) &&
                Objects.equals(name, applicant.name) &&
                location == applicant.location &&
                desiredJobType == applicant.desiredJobType &&
                skill == applicant.skill;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, desiredJobType, skill);
    }
}
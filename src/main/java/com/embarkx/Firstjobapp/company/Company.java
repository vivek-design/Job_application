package com.embarkx.Firstjobapp.company;


import com.embarkx.Firstjobapp.job.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String description;

@JsonIgnore
@OneToMany (mappedBy="company")
    private List<Job>jobs;

    public List<Job> getReviews() {
        return reviews;
    }

    public void setReviews(List<Job> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(mappedBy = "company")
private  List<Job>reviews;

   public Company(){

   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Company(Long id, String name, String description, List<Job> jobs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobs = jobs;
    }
}

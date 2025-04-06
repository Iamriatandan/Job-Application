package com.tandan.firstjobapp.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tandan.firstjobapp.job.Job;
import com.tandan.firstjobapp.reviews.Reviews;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    //every company will have many jobs
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;
    @OneToMany(mappedBy = "company")
    private List<Reviews> reviews;
    public Company() {
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
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
}

package com.example.eindopdrachtbackendv1.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue
    private Long portfolioID;


// relations
    @OneToOne
    private User users;
    @OneToMany
    private List<Upload> uploads;

    public Portfolio() {

    }

    public Portfolio(Long portfolioID, User users, List<Upload> uploads) {
        this.portfolioID = portfolioID;
        this.users = users;
        this.uploads = uploads;
    }

    public Long getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(Long portfolioID) {
        this.portfolioID = portfolioID;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public List<Upload> getUploads() {
        return uploads;
    }

    public void setUploads(List<Upload> uploads) {
        this.uploads = uploads;
    }
}

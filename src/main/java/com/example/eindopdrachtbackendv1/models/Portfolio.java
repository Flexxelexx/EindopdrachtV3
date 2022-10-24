package com.example.eindopdrachtbackendv1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Portfolio {

    @Id
    @Column
    private double countFish;
    @Column
    private double countFishingSpot;
    @Column
    private int portfolioID;

    public Portfolio() {

    }

    public Portfolio(double countFish, double countFishingSpot, int portfolioID) {
        this.countFish = countFish;
        this.countFishingSpot = countFishingSpot;
        this.portfolioID = portfolioID;
    }

    public double getCountFish() {
        return countFish;
    }

    public void setCountFish(double countFish) {
        this.countFish = countFish;
    }

    public double getCountFishingSpot() {
        return countFishingSpot;
    }

    public void setCountFishingSpot(double countFishingSpot) {
        this.countFishingSpot = countFishingSpot;
    }

    public int getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(int portfolioID) {
        this.portfolioID = portfolioID;
    }
}
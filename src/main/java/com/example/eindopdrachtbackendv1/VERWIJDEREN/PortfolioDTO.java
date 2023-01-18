package com.example.eindopdrachtbackendv1.DTOS;

import com.example.eindopdrachtbackendv1.models.Portfolio;

public class PortfolioDTO {


    private Long portfolioID;

    public static PortfolioDTO fromPortfolio (Portfolio portfolio) {

        PortfolioDTO dto = new PortfolioDTO();

        dto.setPortfolioID(portfolio.getPortfolioID());

        return dto;
    }

    public static Portfolio toPortfolio(PortfolioDTO portfolioDTO) {

        Portfolio portfolio = new Portfolio();

        portfolio.setPortfolioID(portfolioDTO.getPortfolioID());

        return portfolio;
    }

    public Long getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(Long portfolioID) {
        this.portfolioID = portfolioID;
    }
}

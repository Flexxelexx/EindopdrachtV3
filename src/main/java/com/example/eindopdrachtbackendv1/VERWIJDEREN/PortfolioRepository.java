package com.example.eindopdrachtbackendv1.Repositories;

import com.example.eindopdrachtbackendv1.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository <Portfolio, Long> {
}

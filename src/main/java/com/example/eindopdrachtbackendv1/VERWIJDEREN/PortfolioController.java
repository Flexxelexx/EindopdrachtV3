package com.example.eindopdrachtbackendv1.Controllers;

import com.example.eindopdrachtbackendv1.DTOS.PortfolioDTO;
import com.example.eindopdrachtbackendv1.Services.PortfolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;


    @GetMapping(value = "")
    public ResponseEntity<List<PortfolioDTO>> getAllPortfolios() {

        List<PortfolioDTO> portfolioDTOS = portfolioService.getAllPortfolios();

        return ResponseEntity.ok().body(portfolioDTOS);
    }

    @GetMapping(value = "/{portfolio}")
    public ResponseEntity<PortfolioDTO> getPortfolio(@PathVariable("portfolio") Long id) {

        PortfolioDTO portfolioDTO = portfolioService.getPortfolio(id);

        return ResponseEntity.ok().body(portfolioDTO);
    }

    @PostMapping(value = "/create")
    public ResponseEntity <PortfolioDTO> createPortfolio (@RequestBody PortfolioDTO id) {

        Long newPortfolio = portfolioService.createPortfolio(id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{portfolio}")
                .buildAndExpand(newPortfolio).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePortfolio(@PathVariable Long id) {
        portfolioService.deletePortfolio(id);
        return ResponseEntity.noContent().build();
    }


}

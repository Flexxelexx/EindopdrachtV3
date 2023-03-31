package com.example.eindopdrachtbackendv1.controllers;

import com.example.eindopdrachtbackendv1.dtos.input.GearInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.GearOutputDto;
import com.example.eindopdrachtbackendv1.services.GearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/gears")
public class GearController {

    @Autowired
    private GearService gearService;

    @GetMapping(value = "")
    public ResponseEntity<List<GearOutputDto>> getGears() {

        List<GearOutputDto> gearDTOS = gearService.getGears();

        return ResponseEntity.ok().body(gearDTOS);
    }

    @GetMapping(value = "/{gear}")
    public ResponseEntity<GearOutputDto> getGear(@PathVariable("gear") Long id) {

        GearOutputDto optionalGear = gearService.getGear(id);

        return ResponseEntity.ok().body(optionalGear);
    }

    @PostMapping
    public ResponseEntity<GearInputDto> createGear(@RequestBody GearInputDto id) {

        String newGear = gearService.createGear(id).toString();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(newGear).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("")
    public ResponseEntity<GearOutputDto> updateGear(@Valid @RequestBody GearInputDto gearInput) {

        GearOutputDto gear = gearService.updateGear(gearInput);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(gear).toUri();

        return ResponseEntity.created(uri).body(gear);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteGear (@PathVariable Long id) {
        gearService.deleteGear(id);
        return ResponseEntity.noContent().build();
    }
}

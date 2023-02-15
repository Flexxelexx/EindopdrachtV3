package com.example.eindopdrachtbackendv1.services;

import com.example.eindopdrachtbackendv1.dtos.input.GearInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.GearOutputDto;
import com.example.eindopdrachtbackendv1.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.repositories.GearRepository;
import com.example.eindopdrachtbackendv1.models.Gear;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GearService {

    private final GearRepository gearRepository;

    public GearService (GearRepository gearRepository) {
        this.gearRepository = gearRepository;
    }

    public List<GearOutputDto> getGears() {
        List <GearOutputDto> collection = new ArrayList<>();
        List <Gear> list = gearRepository.findAll();
        for (Gear gear : list) {
            collection.add(gearToGearOutputDto(gear));
        }
        return collection;
    }

    public GearOutputDto getGear (Long id) {
        GearOutputDto dto;
        Optional<Gear> gearOptional = gearRepository.findById(id);
        if (gearOptional.isPresent()) {
            dto = gearToGearOutputDto(gearOptional.get());
        } else {
            throw new RecordNotFoundException(id.toString());
        }
        return dto;
    }

    public Long createGear (GearInputDto gearDTO) {
        Gear newGear = gearRepository.save(gearInputDtoToGear(gearDTO));
        return newGear.getId();
    }

    private GearOutputDto gearToGearOutputDto (Gear gear) {

        GearOutputDto gearOutputDto = new GearOutputDto();

        gearOutputDto.setId(gear.getId());
        gearOutputDto.setRodLength(gear.getRodLength());
        gearOutputDto.setKindOfReel(gear.getKindOfReel());
        gearOutputDto.setLure(gear.getLure());
        gearOutputDto.setLine(gear.getLine());

        return gearOutputDto;
    }

    private Gear gearInputDtoToGear (GearInputDto gearInputDto) {

        Gear gear = new Gear();

        gear.setId(gearInputDto.getId());
        gear.setRodLength(gearInputDto.getRodLength());
        gear.setKindOfReel(gearInputDto.getKindOfReel());
        gear.setLure(gearInputDto.getLure());
        gear.setLine(gearInputDto.getLine());

        return gear;
    }

    public GearOutputDto updateGear (GearInputDto gearInput) {

        Long inputId = gearInput.getId();

        Gear gear = gearRepository.findById(inputId)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Gear with id %d not found", inputId)));

        gearRepository.save(gear);

        return gearToGearOutputDto(gear);
    }

    public void deleteGear (Long id) {
        gearRepository.deleteById(id);
    }
}

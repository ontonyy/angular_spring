package com.ontony.backend.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ontony.backend.models.Hero;
import com.ontony.backend.models.HeroDto;

@Component
public class HeroMapper {

    public HeroDto mapHeroToDto(Hero hero) {
        return HeroDto.builder()
                      .name(hero.getName())
                      .power(hero.getPower())
                      .build();
    }

    public List<HeroDto> mapListOfHeroToDto(List<Hero> heroes) {
        List<HeroDto> dtos = new LinkedList<>();
        for (final Hero hero : heroes) {
            dtos.add(mapHeroToDto(hero));
        }
        return dtos;
    }

}

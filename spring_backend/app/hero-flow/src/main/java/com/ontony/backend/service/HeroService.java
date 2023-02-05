package com.ontony.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ontony.backend.models.Hero;
import com.ontony.backend.models.HeroDto;
import com.ontony.backend.models.requests.HeroRequest;
import com.ontony.backend.models.SimpleResponse;
import com.ontony.backend.models.requests.HeroUpdateRequest;
import com.ontony.backend.repository.HeroDao;
import com.ontony.backend.repository.HeroMapper;
import com.ontony.backend.repository.HeroRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeroService {
    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;
    private final HeroDao heroDao;

    public List<HeroDto> findAll() {
        return heroMapper.mapListOfHeroToDto(heroRepository.findAll());
    }

    @Transactional

    public SimpleResponse create(HeroRequest heroRequest) {
        Hero hero = new Hero(heroRequest.name(), heroRequest.power());
        heroRepository.save(hero);

        String message = String.format("Create hero with: %s", heroRequest);
        log.info(message);
        return new SimpleResponse(message);
    }

    public HeroDto findById(Long id) {
        Optional<Hero> hero = heroRepository.findById(id);
        log.info("Hero by id: {}", id);
        return hero.map(heroMapper::mapHeroToDto).orElse(null);
    }

    @Transactional
    public SimpleResponse update(HeroUpdateRequest heroRequest) {
        Optional<Hero> hero = heroRepository.findByNameAndPower(heroRequest.hero().name(), heroRequest.hero().power());
        String message;
        if (hero.isPresent()) {
            Hero updateHero = updateByRequest(hero.get(), heroRequest.updateHero());
            heroRepository.save(updateHero);
            message = String.format("Update hero with: %s", heroRequest.hero());
        } else {
            message = String.format("Hero not found: %s", heroRequest.hero());
        }
        log.info(message);
        return new SimpleResponse(message);
    }

    private Hero updateByRequest(Hero hero, HeroRequest updateHero) {
        if (updateHero.name() != null) {
            hero.setName(updateHero.name());
        }
        if (updateHero.power() != null) {
            hero.setPower(updateHero.power());
        }
        return hero;
    }

    @Transactional
    public SimpleResponse deleteByNameAndPower(String name, Integer power) {
        heroRepository.deleteByNameAndPower(name, power);
        String message = String.format("Delete hero with: %s", name);
        log.info(message);
        return new SimpleResponse(message);
    }

    public List<HeroDto> findHeroesByTerm(String term) {
        return heroMapper.mapListOfHeroToDto(heroDao.findHeroesByTerm(term));
    }

    public HeroDto findByNameAndPower(String name, Integer power) {
        Optional<Hero> hero = heroRepository.findByNameAndPower(name, power);
        return hero.map(heroMapper::mapHeroToDto).orElse(null);
    }

    public HeroDto findByName(final String name) {
        Optional<Hero> hero = heroRepository.findByName(name);
        return hero.map(heroMapper::mapHeroToDto).orElse(null);
    }

    public List<HeroDto> findBest(Integer size) {
        return heroMapper.mapListOfHeroToDto(heroRepository.findAllByOrderByPowerDesc().subList(0, size));
    }

    public List<HeroDto> findAllByPagination(Integer size, Integer page) {
        page++;
        return heroMapper.mapListOfHeroToDto(heroDao.findHeroesByPagination(size, page));
    }
}

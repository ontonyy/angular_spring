package com.ontony.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ontony.backend.models.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    Optional<Hero> findByNameAndPower(String name, Integer power);
    Optional<Hero> findByName(String name);
    void deleteByNameAndPower(String name, Integer power);

    List<Hero> findAllByOrderByPowerDesc();
}

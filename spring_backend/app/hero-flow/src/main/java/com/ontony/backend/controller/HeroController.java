package com.ontony.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ontony.backend.models.HeroDto;
import com.ontony.backend.models.requests.HeroRequest;
import com.ontony.backend.models.SimpleResponse;
import com.ontony.backend.models.requests.HeroUpdateRequest;
import com.ontony.backend.service.HeroService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/hero")
public class HeroController {
    private final HeroService heroService;
    @GetMapping("/hello")
    public String hello() {
        return "Simple hello";
    }

    @GetMapping("/all")
    public List<HeroDto> findAll() {
        return heroService.findAll();
    }

    @GetMapping("/all/paged")
    public List<HeroDto> findAllByPagination(@RequestParam("size") Integer size, @RequestParam("page") Integer page) {
        return heroService.findAllByPagination(size, page);
    }

    @GetMapping("/best")
    public List<HeroDto> findBest(@RequestParam("size") Integer size) {
        return heroService.findBest(size);
    }

    @PostMapping
    public SimpleResponse create(@RequestBody HeroRequest heroRequest) {
        return heroService.create(heroRequest);
    }

    @GetMapping("/{name}")
    public HeroDto findByName(@PathVariable("name") String name) {
        return heroService.findByName(name);
    }

    @DeleteMapping
    public SimpleResponse deleteByNameAndPower(@RequestParam("name") String name, @RequestParam("power") Integer power) {
        return heroService.deleteByNameAndPower(name, power);
    }

    @PutMapping("/update")
    public SimpleResponse update(@RequestBody HeroUpdateRequest heroRequest) {
        return heroService.update(heroRequest);
    }

    @GetMapping("/search/{term}")
    public List<HeroDto> findHeroesByTerm(@PathVariable String term) {
        return heroService.findHeroesByTerm(term);
    }
}

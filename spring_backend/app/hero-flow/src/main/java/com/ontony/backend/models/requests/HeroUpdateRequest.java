package com.ontony.backend.models.requests;

public record HeroUpdateRequest(HeroRequest hero, HeroRequest updateHero) {
}

package com.ontony.backend.models;

import lombok.Builder;

@Builder
public record HeroDto(String name, Integer power) {
}

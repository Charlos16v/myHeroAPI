package com.example.myHero.api.service;

import com.example.myHero.model.Hero;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface HeroService {
    @Cacheable("heroCache")
    List<Hero> getAll();

    @Cacheable("heroCache")
    Optional<Hero> findById(Long id);

    @Cacheable("heroCache")
    List<Hero> searchHeroesByName(String searchParameter);

    Hero createHero(Hero hero);

    Hero modifyHero(Long id, Hero updatedHero);

    void deleteHeroById(Long id);
}


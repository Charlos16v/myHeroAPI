package com.example.myHero.hero;

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

    @Cacheable("heroCache")
    Hero createHero(Hero hero);

    @Cacheable("heroCache")
    Hero modifyHero(Long id, Hero updatedHero);

    @Cacheable("heroCache")
    void deleteHeroById(Long id);
}


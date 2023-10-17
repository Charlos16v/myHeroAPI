package com.example.myHero.hero;

import java.util.List;
import java.util.Optional;

public interface HeroService {
    List<Hero> getAll();

    Optional<Hero> findById(Long id);

    List<Hero> searchHeroesByName(String searchParameter);

    Hero createHero(Hero hero);
}


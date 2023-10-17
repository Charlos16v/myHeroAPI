package com.example.myHero.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroRepository _heroRepository;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository) {
        this._heroRepository = heroRepository;
    }

    @Override
    public List<Hero> getAll() {
        return _heroRepository.findAll();
    }

    @Override
    public Optional<Hero> findById(Long id) {
        return _heroRepository.findById(id);
    }

    @Override
    public List<Hero> searchHeroesByName(String searchParameter) {
        return _heroRepository.findByNameContainingIgnoreCase(searchParameter);
    }

}

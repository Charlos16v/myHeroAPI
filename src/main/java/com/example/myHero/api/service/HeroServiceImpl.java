package com.example.myHero.api.service;

import com.example.myHero.exception.HeroNotFoundException;
import com.example.myHero.model.Hero;
import com.example.myHero.repository.HeroRepository;
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

    @Override
    public Hero createHero(Hero hero) {
        return _heroRepository.saveAndFlush(hero);
    }

    @Override
    public void modifyHero(Long id, Hero updatedHero) {
        Optional<Hero> heroOptional = _heroRepository.findById(id);

        if (heroOptional.isPresent()) {
            Hero hero = heroOptional.get();
            hero.setName(updatedHero.getName());
            hero.setDescription(updatedHero.getDescription());
            hero.setDob(updatedHero.getDob());
            _heroRepository.save(hero);
        } else {
            throw new HeroNotFoundException("Hero not found with ID: " + id);
        }
    }

    @Override
    public void deleteHeroById(Long id) {
        Optional<Hero> heroOptional = _heroRepository.findById(id);
        if (heroOptional.isPresent()) {
            _heroRepository.delete(heroOptional.get());
        } else {
            throw new HeroNotFoundException("Hero not found with ID: " + id);
        }
    }

}

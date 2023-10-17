package com.example.myHero.hero;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hero")
public class HeroController {

    private final HeroRepository _heroRepository;

    public HeroController(HeroRepository heroRepository) {
        this._heroRepository = heroRepository;
    }

    @GetMapping
    public ResponseEntity<List<Hero>> getAll() {
        List<Hero> heroes = _heroRepository.findAll();
        return ResponseEntity.ok(heroes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Hero> getById(@PathVariable Long id) {
        Optional<Hero> hero = _heroRepository.findById(id);

        return hero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

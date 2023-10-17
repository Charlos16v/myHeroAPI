package com.example.myHero.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hero")
public class HeroController {

    private final HeroService _heroService;

    @Autowired
    public HeroController(HeroService heroService) {
        this._heroService = heroService;
    }

    @GetMapping
    public ResponseEntity<List<Hero>> getAll() {
        List<Hero> heroes = _heroService.getAll();
        return ResponseEntity.ok(heroes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getById(@PathVariable Long id) {
        Optional<Hero> hero = _heroService.findById(id);

        return hero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

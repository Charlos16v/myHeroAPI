package com.example.myHero.hero;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all heroes", description = "Returns a list of all heroes.")
    @ApiResponse(responseCode = "200", description = "List of heroes retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hero.class)))
    public ResponseEntity<List<Hero>> getAll() {
        List<Hero> heroes = _heroService.getAll();
        return ResponseEntity.ok(heroes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a hero by ID", description = "Returns a hero based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "Hero retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hero.class)))
    @ApiResponse(responseCode = "404", description = "Hero not found")
    public ResponseEntity<Hero> getById(@PathVariable Long id) {
        Optional<Hero> hero = _heroService.findById(id);

        return hero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @Operation(summary = "Search heroes by name", description = "Returns heroes whose names contain the provided search parameter.")
    @ApiResponse(
            responseCode = "200",
            description = "Heroes found successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hero.class))
    )
    public ResponseEntity<List<Hero>> searchHeroes(@RequestParam String searchParameter) {
        List<Hero> matchingHeroes = _heroService.searchHeroesByName(searchParameter);
        return ResponseEntity.ok(matchingHeroes);
    }
}

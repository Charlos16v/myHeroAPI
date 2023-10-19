package com.example.myHero.api.controller;

import com.example.myHero.aspect.CustomTimed;
import com.example.myHero.api.service.HeroService;
import com.example.myHero.exception.HeroExceptionHandler;
import com.example.myHero.exception.HeroNotFoundException;
import com.example.myHero.model.Hero;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Operation(
            summary = "Get all heroes",
            description = "Returns a list of all heroes."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of heroes retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hero.class))
    )
    @CustomTimed("GetAll Heroes")
    public ResponseEntity<List<Hero> >getAll() {
        return ResponseEntity.ok(_heroService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a hero by ID",
            description = "Returns a hero based on the provided ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Hero retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hero.class))
    )
    @ApiResponse(responseCode = "404", description = "Hero not found")
    @CustomTimed("GetById Hero")
    public ResponseEntity<Hero> getById(@PathVariable Long id) {
        Optional<Hero> hero = _heroService.findById(id);
        return hero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search heroes by name",
            description = "Returns heroes whose names contain the provided search parameter."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Heroes found successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hero.class))
    )
    @CustomTimed("Search Heroes by name")
    public ResponseEntity<List<Hero>> searchHeroes(@RequestParam String searchParameter) {
        List<Hero> matchingHeroes = _heroService.searchHeroesByName(searchParameter);
        return ResponseEntity.ok(matchingHeroes);
    }

    @PostMapping
    @Operation(
            summary = "Create a new hero",
            description = "Creates a new hero and returns the created hero."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Hero created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hero.class))
    )
    @CustomTimed("CreateHeroe")
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {
        Hero createdHero = _heroService.createHero(hero);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHero);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Modify a hero by ID",
            description = "Modifies a hero based on the provided ID."
    )
    @ApiResponse(responseCode = "200", description = "Hero modified successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hero.class)))
    @ApiResponse(responseCode = "404", description = "Hero not found")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @CustomTimed("ModifyHeroe")
    public ResponseEntity modifyHero(@PathVariable Long id, @RequestBody Hero updatedHero) {
        try {
            _heroService.modifyHero(id, updatedHero);
            return ResponseEntity.ok().build();
        } catch (HeroNotFoundException e) {
            return HeroExceptionHandler.handleHeroNotFoundException(e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a hero by ID",
            description = "Deletes a hero based on the provided ID."
    )
    @ApiResponse(responseCode = "204", description = "Hero deleted successfully")
    @ApiResponse(responseCode = "404", description = "Hero not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @CustomTimed("DeleteHeroe")
    public ResponseEntity deleteHero(@PathVariable Long id) {
        try {
            _heroService.deleteHeroById(id);
            return ResponseEntity.noContent().build();
        } catch (HeroNotFoundException e) {
            return HeroExceptionHandler.handleHeroNotFoundException(e);
        }
    }
}

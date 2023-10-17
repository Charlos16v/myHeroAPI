package com.example.myHero.hero;

import com.example.myHero.exception.HeroNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HeroServiceImplTest {

    @Mock
    private HeroRepository heroRepository;

    private HeroServiceImpl heroService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        heroService = new HeroServiceImpl(heroRepository);
    }

    @Test
    public void testGetAll() {
        List<Hero> heroes = List.of(new Hero(), new Hero(), new Hero());
        Mockito.when(heroRepository.findAll()).thenReturn(heroes);

        List<Hero> result = heroService.getAll();

        assertEquals(heroes, result);
    }

    @Test
    public void testFindByIdHeroFound() {
        Long heroId = 1L;
        Hero hero = new Hero();
        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.of(hero));

        Optional<Hero> result = heroService.findById(heroId);

        assertEquals(Optional.of(hero), result);
    }

    @Test
    public void testFindByIdHeroNotFound() {
        Long heroId = 1L;
        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.empty());

        Optional<Hero> result = heroService.findById(heroId);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testSearchHeroesByName() {
        String searchParameter = "Superman";
        List<Hero> heroes = List.of(new Hero(), new Hero());
        Mockito.when(heroRepository.findByNameContainingIgnoreCase(searchParameter)).thenReturn(heroes);

        List<Hero> result = heroService.searchHeroesByName(searchParameter);

        assertEquals(heroes, result);
    }

    @Test
    public void testCreateHero() {
        Hero hero = new Hero();
        Mockito.when(heroRepository.saveAndFlush(hero)).thenReturn(hero);

        Hero result = heroService.createHero(hero);

        assertEquals(hero, result);
    }

    @Test
    public void testModifyHeroFound() {
        Long heroId = 1L;
        Hero updatedHero = new Hero();
        Hero existingHero = new Hero();
        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.of(existingHero));
        Mockito.when(heroRepository.save(existingHero)).thenReturn(existingHero);

        Hero result = heroService.modifyHero(heroId, updatedHero);

        assertEquals(existingHero, result);
    }

    @Test
    public void testModifyHeroNotFound() {
        Long heroId = 1L;
        Hero updatedHero = new Hero();
        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.empty());

        assertThrows(HeroNotFoundException.class, () -> heroService.modifyHero(heroId, updatedHero));
    }

    @Test
    public void testDeleteHeroByIdFound() {
        Long heroId = 1L;
        Hero hero = new Hero();
        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.of(hero));

        heroService.deleteHeroById(heroId);

        Mockito.verify(heroRepository).delete(hero);
    }

    @Test
    public void testDeleteHeroByIdNotFound() {
        Long heroId = 1L;
        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.empty());

        assertThrows(HeroNotFoundException.class, () -> heroService.deleteHeroById(heroId));
    }
}


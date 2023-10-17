package com.example.myHero.hero;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {

    private Hero hero;

    @BeforeEach
    void setUp() {
        hero = new Hero("Aragorn", "The King of Gondor", LocalDate.of(1970, Month.JANUARY, 1));
    }

    @Test
    void testConstructorWithId() {
        assertEquals(0, hero.getId());
        assertEquals("Aragorn", hero.getName());
        assertEquals("The King of Gondor", hero.getDescription());
        assertEquals(LocalDate.of(1970, Month.JANUARY, 1), hero.getDob());
    }

    @Test
    void testConstructorWithoutId() {
        Hero heroWithoutId = new Hero("Legolas", "Elven Archer", LocalDate.of(2931, Month.AUGUST, 24));
        assertEquals("Legolas", heroWithoutId.getName());
        assertEquals("Elven Archer", heroWithoutId.getDescription());
        assertEquals(LocalDate.of(2931, Month.AUGUST, 24), heroWithoutId.getDob());
    }

    @Test
    void testGetAge() {
        int expectedAge = LocalDate.now().getYear() - LocalDate.of(1970, Month.JANUARY, 1).getYear();
        assertEquals(expectedAge, hero.getAge());
    }

    @Test
    void testSetters() {
        hero.setId(2);
        hero.setName("Gimli");
        hero.setDescription("Dwarf Warrior");
        hero.setDob(LocalDate.of(2879, Month.NOVEMBER, 21));

        assertEquals(2, hero.getId());
        assertEquals("Gimli", hero.getName());
        assertEquals("Dwarf Warrior", hero.getDescription());
        assertEquals(LocalDate.of(2879, Month.NOVEMBER, 21), hero.getDob());
    }
}

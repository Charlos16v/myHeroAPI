package com.example.myHero.repository;

import com.example.myHero.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long>{
    List<Hero> findByNameContainingIgnoreCase(String searchParameter);
}

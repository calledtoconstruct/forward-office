package com.example.golink;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcronymRepository extends JpaRepository<Acronym, Integer> {
    List<Acronym> findByAbbreviationStartsWithOrderByAbbreviation(String prefix);
    List<Acronym> findAllByOrderByDateCreatedDesc(Pageable pageable);
}

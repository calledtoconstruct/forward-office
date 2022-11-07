package com.example.golink;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoRepository extends JpaRepository<Entry, String> {
    
}

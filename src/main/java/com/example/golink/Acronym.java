package com.example.golink;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Acronym")
@NoArgsConstructor
public class Acronym implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acronym_id")
    private Integer acronymId;

    @Column(name = "abbreviation", length = 10)
    private String abbreviation;

    @Column(name = "brief", length = 256)
    private String brief;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created")
    private Date dateCreated;
}
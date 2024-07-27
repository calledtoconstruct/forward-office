package com.example.golink;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Entry")
@NoArgsConstructor
public class Entry implements Serializable {
    
    @Id
    @Column(name = "name", length = 30)
    private String shortName;

    @Column(name = "url", length = 2048)
    private String longName;
}

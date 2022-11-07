package com.example.golink;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LookupService {

    @Autowired
    private GoRepository goRepository;

    @Autowired
    private AcronymRepository acronymRepository;
    
    public Optional<String> getLongName(String shortName) {
        final var maybeEntry = goRepository.findById(shortName);
        if (maybeEntry.isPresent()) {
            final var entry = maybeEntry.get();
            return Optional.of(entry.getLongName());
        }
        return Optional.empty();
    }

    public void addEntry(String shortName, String longName) {
        final var entry = new Entry();
        entry.setShortName(shortName);
        entry.setLongName(longName);
        goRepository.save(entry);
    }

    public void removeEntry(String shortName) {
        goRepository.deleteById(shortName);
    }

    public List<Entry> listEntries() {
        return goRepository.findAll();
    }

    public List<Acronym> listAcronyms() {
        return acronymRepository.findAllByOrderByDateCreatedDesc(Pageable.ofSize(10));
    }

    public List<Acronym> listAcronymsMatching(final String prefix) {
        return acronymRepository.findByAbbreviationStartsWithOrderByAbbreviation(prefix);
    }

    public void addAcronym(String abbreviation, String brief, String description) {
        final var acronym = new Acronym();
        acronym.setAcronymId(null);
        acronym.setAbbreviation(abbreviation);
        acronym.setBrief(brief);
        acronym.setDescription(description);
        acronymRepository.save(acronym);
    }

    public void removeAcronym(int acronymId) {
        acronymRepository.deleteById(acronymId);
    }

}

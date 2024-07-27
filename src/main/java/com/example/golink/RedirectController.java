package com.example.golink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RedirectController {

    @Autowired
    private LookupService lookupService;

    @GetMapping("/go")
    public String list(final Model model) {
        model.addAttribute("entries", lookupService.listEntries());
        return "go";
    }

    @GetMapping("/style.css")
    public String styles() {
        return "style.css";
    }

    @PostMapping(path = "/entry/add")
    public String addEntry(final @RequestParam MultiValueMap<String, String> map, final Model model) {
        final var shortName = map.getFirst("shortName");
        final var longName = map.getFirst("longName");
        lookupService.addEntry(shortName, longName);
        model.addAttribute("title", "Go Link Editor");
        model.addAttribute("operation", "add");
        model.addAttribute("thing", "an Entry");
        model.addAttribute("return_to", "go");
        return "message";
    }

    @PostMapping("/entry/remove")
    public String removeEntry(final @RequestParam MultiValueMap<String, String> map, final Model model) {
        final var shortName = map.getFirst("shortName");
        lookupService.removeEntry(shortName);
        model.addAttribute("title", "Go Link Editor");
        model.addAttribute("operation", "delete");
        model.addAttribute("thing", "an Entry");
        model.addAttribute("return_to", "go");
        return "message";
    }

    @GetMapping("/acronym")
    public String acronym(final Model model) {
        model.addAttribute("acronyms", lookupService.listAcronyms());
        return "acronym";
    }

    @GetMapping("/acronym/search")
    public String acronymSearch(final @RequestParam String abbreviation, final Model model) throws InterruptedException {
        Thread.sleep(1000);
        model.addAttribute("acronyms", lookupService.listAcronymsMatching(abbreviation));
        return "acronym_list";
    }

    @GetMapping("/acronym/recent")
    public String recentAcronyms(final Model model) {
        model.addAttribute("acronyms", lookupService.listAcronyms());
        return "acronym_list";
    }

    @PostMapping(path = "/acronym/add")
    public String addAcronym(final @RequestParam MultiValueMap<String, String> map, final Model model) {
        final var abbreviation = map.getFirst("abbreviation");
        final var brief = map.getFirst("brief");
        final var description = map.getFirst("description");
        lookupService.addAcronym(abbreviation, brief, description);
        model.addAttribute("title", "Acronym Editor");
        model.addAttribute("operation", "add");
        model.addAttribute("thing", "an Acronym");
        model.addAttribute("return_to", "acronym");
        return "message";
    }

    @PostMapping("/acronym/remove")
    public String removeAcronym(final @RequestParam MultiValueMap<String, String> map, final Model model) {
        final var string_acronymId = map.getFirst("acronymId");
        final var acronymId = Integer.parseInt(string_acronymId);
        lookupService.removeAcronym(acronymId);
        model.addAttribute("title", "Acronym Editor");
        model.addAttribute("operation", "delete");
        model.addAttribute("thing", "an Acronym");
        model.addAttribute("return_to", "acronym");
        return "message";
    }

    @GetMapping("/{shortName}")
    public ResponseEntity<String> lookup(final @PathVariable String shortName) {
        return lookupService.getLongName(shortName)
            .map(RedirectController::redirect)
            .orElseGet(RedirectController::notFound);
    }

    private static ResponseEntity<String> redirect(final String longName) {
        return ResponseEntity
        .status(HttpStatus.SEE_OTHER)
        .header("Location", longName)
        .body("Thank you for using our service");
    }

    private static ResponseEntity<String> notFound() {
        return ResponseEntity.notFound().build();
    }

}

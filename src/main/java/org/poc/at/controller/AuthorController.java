package org.poc.at.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.poc.at.model.Author;
import org.poc.at.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Tag(name = "Author", description = "Author management APIs")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Operation(summary = "Get all authors")
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @Operation(summary = "Get author by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return authorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new author")
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @Operation(summary = "Delete an author by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


package org.poc.at.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.poc.at.model.Book;
import org.poc.at.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book", description = "Book management APIs")
public class BookController {
    @Autowired
    private BookService bookService;

    @Operation(summary = "Get all books")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @Operation(summary = "Get book by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new book")
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @Operation(summary = "Delete a book by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

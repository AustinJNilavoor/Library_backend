package com.example.Library.controller;

import com.example.Library.model.Book;
import com.example.Library.repository.BookRepository;

import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBooksById(@PathVariable Long id) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Book with ID " + id + " not found"));
        return ResponseEntity.ok(book);
        // return repo.findById(id);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam(required = false) String author) {
        if (author == null || author.trim().isEmpty()) {
            return ResponseEntity.ok(repo.findAll());
        }
        List<Book> books = repo.findByAuthorIgnoreCase(author);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return repo.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Book with ID " + id + " not found"));

        if (updatedBook.getTitle() != null)
            book.setTitle(updatedBook.getTitle());
        if (updatedBook.getAuthor() != null)
            book.setAuthor(updatedBook.getAuthor());
        if (updatedBook.getGenre() != null)
            book.setGenre(updatedBook.getGenre());
        if (updatedBook.getPublishedYear() != null)
            book.setPublishedYear(updatedBook.getPublishedYear());

        return ResponseEntity.ok(repo.save(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with ID " + id + " not found");
        }

        repo.deleteById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Book with ID " + id + " deleted successfully");

        return ResponseEntity.ok(response);
    }

}

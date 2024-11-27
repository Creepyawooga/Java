package org.example;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooksByCategory(@RequestParam String category) {
        return bookService.findBooksByCategory(category);
    }

    @GetMapping("/{title}/{author}")
    public Book getBookByTitleAndAuthor(@PathVariable String title, @PathVariable String author) {
        return bookService.findBookByTitleAndAuthor(title, author);
    }

    @PostMapping
    public Book createBook(@RequestParam String title, @RequestParam String author, @RequestParam String category) {
        return bookService.createBook(title, author, category);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestParam String title, @RequestParam String author, @RequestParam String category) {
        bookService.updateBook(id, title, author, category);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}

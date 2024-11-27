package org.example;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Cacheable(value = "bookByTitleAndAuthor", key = "#title + '-' + #author")
    public Book findBookByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }

    @Cacheable(value = "booksByCategory", key = "#categoryName")
    public List<Book> findBooksByCategory(String categoryName) {
        return bookRepository.findAllByCategoryName(categoryName);
    }

    @CacheEvict(value = {"bookByTitleAndAuthor", "booksByCategory"}, allEntries = true)
    public Book createBook(String title, String author, String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            category = categoryRepository.save(category);
        }
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        return bookRepository.save(book);
    }

    @CacheEvict(value = {"bookByTitleAndAuthor", "booksByCategory"}, allEntries = true)
    public void updateBook(Long id, String title, String author, String categoryName) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(title);
        book.setAuthor(author);
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            category = categoryRepository.save(category);
        }
        book.setCategory(category);
        bookRepository.save(book);
    }

    @CacheEvict(value = {"bookByTitleAndAuthor", "booksByCategory"}, allEntries = true)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

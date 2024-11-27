package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BookService bookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        return new BookService(bookRepository, categoryRepository);
    }
}

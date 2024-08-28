package com.example.bookstore;

import org.springframework.hateoas.RepresentationModel;

public class BookDTO extends RepresentationModel<BookDTO> {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private String isbn;
    
    // Getters and Setters
    // ...
}

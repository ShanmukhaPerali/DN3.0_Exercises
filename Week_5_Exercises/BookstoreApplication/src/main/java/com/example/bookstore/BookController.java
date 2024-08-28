package com.example.bookstore;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bookstore.mapper.*;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> bookList = new ArrayList<>();
    private final EntityMapper entityMapper = EntityMapper.INSTANCE;
    @Autowired
    private MeterRegistry meterRegistry;
    // POST method to add a new book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book) {
        bookList.add(book);
        return book;
    }

    // GET method to retrieve all books
    @GetMapping(produces = { "application/json", "application/xml" })
    public List<Book> getAllBooks() {
        return bookList;
    }

    // GET method to retrieve a book by its ID with HATEOAS links
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
    public ResponseEntity<EntityModel<BookDTO>> getBookById(@PathVariable Long id) {
        Book book = bookList.stream()
                            .filter(b -> b.getId().equals(id))
                            .findFirst()
                            .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        // Mapping Book entity to BookDTO
        BookDTO bookDTO = entityMapper.bookToBookDTO(book);

        // Adding HATEOAS links
        EntityModel<BookDTO> bookResource = EntityModel.of(bookDTO);
        Link selfLink = linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel();
        Link booksLink = linkTo(methodOn(BookController.class).getAllBooks()).withRel("books");
        bookResource.add(selfLink, booksLink);

        return ResponseEntity.ok(bookResource);
    }

    // PUT method to update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        Book book = bookList.stream()
                            .filter(b -> b.getId().equals(id))
                            .findFirst()
                            .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());
        book.setIsbn(updatedBook.getIsbn());

        return ResponseEntity.ok(book);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook1(@Valid @RequestBody Book book) {
        bookList.add(book);
        meterRegistry.counter("books.added").increment();  // Increment custom metric
        return book;
    }
    // DELETE method to remove a book
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        Book book = bookList.stream()
                            .filter(b -> b.getId().equals(id))
                            .findFirst()
                            .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        bookList.remove(book);
    }
}

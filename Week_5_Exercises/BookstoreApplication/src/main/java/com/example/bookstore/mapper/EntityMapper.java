package com.example.bookstore.mapper;

import com.example.bookstore.Book;
import com.example.bookstore.BookDTO;
import com.example.bookstore.*;
import com.example.bookstore.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    // Book mappings
    BookDTO bookToBookDTO(Book book);
    Book bookDTOToBook(BookDTO bookDTO);

    // Customer mappings
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}

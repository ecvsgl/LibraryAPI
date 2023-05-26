package com.ecvsgl.libraryproject.controller;


import com.ecvsgl.libraryproject.model.dto.BookRequest;
import com.ecvsgl.libraryproject.model.BookEntity;
import com.ecvsgl.libraryproject.model.dto.BookResponse;
import com.ecvsgl.libraryproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books/")
    public List<BookResponse> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PutMapping("/books/")
    public BookResponse updateBookDetails(@RequestBody BookEntity request){
        return bookService.updateBookDetails(request);
    }

    @PostMapping("/books/")
    public BookResponse addNewBook(@RequestBody BookRequest request){
        return bookService.addNewBook(request);
    }

    @GetMapping("/books/st1")
    public List<BookResponse> getAllAvailableBooks(){
        return bookService.getAllAvailableBooks();
    }
}

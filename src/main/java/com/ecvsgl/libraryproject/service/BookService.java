package com.ecvsgl.libraryproject.service;


import com.ecvsgl.libraryproject.exception.AlreadyExistingBookEntityException;
import com.ecvsgl.libraryproject.exception.CannotFindUpdateResourceException;
import com.ecvsgl.libraryproject.model.dto.BookRequest;
import com.ecvsgl.libraryproject.model.BookEntity;
import com.ecvsgl.libraryproject.model.dto.BookResponse;
import com.ecvsgl.libraryproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BookService {

    private final BookRepository bookRepository;


    public List<BookResponse> getAllBooks() {
        List<BookEntity> searchResult = bookRepository.findAll();
        List<BookResponse> response = new ArrayList<BookResponse>();

        for(BookEntity x: searchResult){
            BookResponse y = BookResponse.builder()
                    .bookName(x.getBookName())
                    .bookAuthor(x.getBookAuthor())
                    .bookISBN(x.getBookISBN())
                    .bookPrice(x.getBookPrice())
                    .checkedOut(x.isCheckedOut())
                    .build();
            response.add(y);
        }
        return response;
    }

    public BookResponse updateBookDetails(BookEntity request) {
        if(!bookRepository.existsById(request.getBookId())) {
            throw new CannotFindUpdateResourceException("Provided database ID is invalid.");
        }
        BookEntity dbBook = bookRepository.findBookEntityByBookId(request.getBookId());
        if(!request.getBookISBN().equals(dbBook.getBookISBN())){
            throw new CannotFindUpdateResourceException("The book with this  database ID does not match with " +
                    "provided ISBN. ISBN updates are not allowed. Please create a new book entry for a new ISBN.");
        } else {
            BookEntity bookItem = bookRepository.save(request);
            return BookResponse.builder()
                    .bookName(bookItem.getBookName())
                    .bookAuthor(bookItem.getBookAuthor())
                    .bookISBN(bookItem.getBookISBN())
                    .bookPrice(bookItem.getBookPrice())
                    .checkedOut(bookItem.isCheckedOut())
                    .build();
        }
    }

    public BookResponse addNewBook(BookRequest request) {
        if(bookRepository.existsBookEntityByBookISBN(request.getBookISBN())){
            throw new AlreadyExistingBookEntityException(
                    "The book with ISBN: " + request.getBookISBN() + " already exists! " +
                            "Cannot add the book into the system.");
        }
        //Mapping Request into Entity
        BookEntity bookItem = BookEntity.builder()
                .bookName(request.getBookName())
                .bookAuthor(request.getBookAuthor())
                .bookPrice(request.getBookPrice())
                .bookISBN(request.getBookISBN())
                .checkedOut(false)
                .build();

        bookItem = bookRepository.save(bookItem);

        //Returning mapped BookEntity into Response

        return BookResponse.builder()
                .bookName(bookItem.getBookName())
                .bookAuthor(bookItem.getBookAuthor())
                .bookISBN(bookItem.getBookISBN())
                .bookPrice(bookItem.getBookPrice())
                .checkedOut(bookItem.isCheckedOut())
                .build();
    }

    public List<BookResponse> getAllAvailableBooks() {
        List<BookEntity> searchResult = bookRepository.findBookEntitiesByCheckedOutIsFalse();
        List<BookResponse> response = new ArrayList<BookResponse>();
        for(BookEntity x: searchResult){
            BookResponse y = BookResponse.builder()
                    .bookName(x.getBookName())
                    .bookAuthor(x.getBookAuthor())
                    .bookISBN(x.getBookISBN())
                    .bookPrice(x.getBookPrice())
                    .checkedOut(x.isCheckedOut())
                    .build();
            response.add(y);
        }
        return response;
    }
}

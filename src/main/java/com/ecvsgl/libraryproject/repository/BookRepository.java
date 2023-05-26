package com.ecvsgl.libraryproject.repository;

import com.ecvsgl.libraryproject.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Integer> {

    boolean existsBookEntityByBookISBN(String bookISBN);

    List<BookEntity> findBookEntitiesByCheckedOutIsFalse();

    BookEntity findBookEntityByBookISBN(String bookISBN);

    BookEntity findBookEntityByBookId(Integer bookId);

}

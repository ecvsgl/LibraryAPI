package com.ecvsgl.libraryproject.initializer;

import com.ecvsgl.libraryproject.exception.LoaderIsNotWorkingException;
import com.ecvsgl.libraryproject.model.BookEntity;
import com.ecvsgl.libraryproject.model.BookEntityLoader;
import com.ecvsgl.libraryproject.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class LibraryDataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if(bookRepository.count()==0){
            BookEntityLoader bookEntityList = restTemplate.getForObject(
                    "https://mocki.io/v1/5546028d-9052-4c40-bb2f-9bb0a56fd8b7", BookEntityLoader.class);
            if(bookEntityList.getBooks()==null | bookEntityList.getBooks().isEmpty()){
                throw new LoaderIsNotWorkingException("Please check the JSON Loader.");
            }else {
                bookRepository.saveAll(bookEntityList.getBooks());
            }
        }
    }
}

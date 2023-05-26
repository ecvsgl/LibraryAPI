package com.ecvsgl.libraryproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestErrorHandler {
    @ExceptionHandler({AlreadyExistingBookEntityException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<BookEntityExceptionTemplate> handleException(AlreadyExistingBookEntityException e){
        BookEntityExceptionTemplate template = getBookEntityExceptionTemplate(e.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<BookEntityExceptionTemplate>(template, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({LoaderIsNotWorkingException.class})
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public ResponseEntity<BookEntityExceptionTemplate> handleException(LoaderIsNotWorkingException e){
        BookEntityExceptionTemplate template = getBookEntityExceptionTemplate(e.getMessage(),HttpStatus.FAILED_DEPENDENCY);
        return new ResponseEntity<BookEntityExceptionTemplate>(template,HttpStatus.FAILED_DEPENDENCY);
    }

    @ExceptionHandler({CannotFindUpdateResourceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BookEntityExceptionTemplate> handleException(CannotFindUpdateResourceException e){
        BookEntityExceptionTemplate template = getBookEntityExceptionTemplate(e.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<BookEntityExceptionTemplate>(template,HttpStatus.BAD_REQUEST);
    }


    // ---------------

    private BookEntityExceptionTemplate getBookEntityExceptionTemplate(String message, HttpStatus status){
        BookEntityExceptionTemplate template = new BookEntityExceptionTemplate();
        template.setExceptionDate(LocalDateTime.now());
        template.setExceptionMessage(message);
        template.setStatusCode(status.value());
        return template;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class BookEntityExceptionTemplate{

        private int statusCode;
        private LocalDateTime exceptionDate;
        private String exceptionMessage;

    }
}

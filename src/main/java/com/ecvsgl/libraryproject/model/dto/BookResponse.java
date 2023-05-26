package com.ecvsgl.libraryproject.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponse {
    private String bookName;
    private String bookAuthor;
    private String bookISBN;
    private Double bookPrice;
    private boolean checkedOut;
}

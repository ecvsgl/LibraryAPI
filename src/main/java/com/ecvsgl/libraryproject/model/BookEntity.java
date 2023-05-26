package com.ecvsgl.libraryproject.model;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer bookId;

    private String bookName;
    private String bookAuthor;
    private String bookISBN;
    private Double bookPrice;
    private boolean checkedOut;

}

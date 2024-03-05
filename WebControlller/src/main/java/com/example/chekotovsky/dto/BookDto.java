package com.example.chekotovsky.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String name;
    private String authort;
    private int yearOfIssue;
    private String genre;
}

package org.example.javalibraryservice.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto{

        Long id;

        @Pattern(message = "Invalid ISBN-13 format",
                regexp = "^978-[0-9]{1}-[0-9]{3}-[0-9]{5}-[0-9]$")
        private String isbn;

        @NotEmpty(message = "Name cannot be empty", groups = Marker.OnCreate.class)
        private String name;

        @NotEmpty(message = "Genre cannot be empty", groups = Marker.OnCreate.class)
        private String genre;

        @NotEmpty(message = "Description cannot be empty", groups = Marker.OnCreate.class)
        private String description;

        @NotEmpty(message = "Author cannot be empty", groups = Marker.OnCreate.class)
        private String author;


};

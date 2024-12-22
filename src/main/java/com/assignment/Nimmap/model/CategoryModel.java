package com.assignment.Nimmap.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {

    private Long id; 

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 4, message = "Name should be at least 4 characters long")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;
}

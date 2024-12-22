package com.assignment.Nimmap.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	private Long id;

	 @NotBlank(message = "Name cannot be blank")
	 @Size(min = 4, message = "Name should be at least 4 characters long")
    private String name;
	 @NotBlank
    private String description;
	 @NotNull
    private double price;
	 
	 private Long categoryId;
}

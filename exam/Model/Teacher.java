package com.example.exam.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    @NotNull(message = "ID should not be empty!")
    private Integer ID;

    @NotEmpty(message = "Name should not be empty!")
    private String name;

    @NotNull(message = "Salary should not be empty!")
    private Double salary;
}

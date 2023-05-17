package com.example.springhw14.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor

public class Employee {

    @NotNull(message = "ID SHOULDN'T BE NULL")
    @Min(value = 3, message = "id length should be more than 2")
    //    Cannot be null
    //Length more than 2
    private String id ;

    @NotNull(message = "NAME SHOULDN'T BE NULL")
    @Min(value = 5, message = "name length should be more than 4")
    //    Cannot be null
    //Length more than 4
    private String name;

    @Pattern(regexp = "~^(1[89]|[2-9][0-9])$~")
    @Positive
    @NotNull(message = "Age SHOULDN'T BE NULL")
    @NumberFormat
    //    Cannot be null
    //It has to be number
    //It must be more than 25
    private int age;


    @NotNull(message = "Role SHOULDN'T BE NULL")
    @Pattern(regexp = "^(supervisor|coordinator)$", message = "Role field allow input: supervisor or coordinator only")
    //    Cannot be null
    //must be supervisor or coordinator only
    private String role;

    @Pattern(regexp = "^(true|false)$", message = "onLeave field allow input: true or false only")
    //must be false
    private boolean onLeave =false;


    @NotNull(message = "Employment Year SHOULDN'T BE NULL")
//  @Pattern(regexp = "^[1-9]?[0-9]{1}$|^100$",message = ("input has to be numbers between1-100")) //has to be number
//    @Pattern(regexp="^(0|[1-9][0-9]*)$")
    @PastOrPresent(message = "year should be past or present")
    @NumberFormat(style = NumberFormat.Style.NUMBER)

    //    Cannot be null
    //it has to be number
    //must be a valid year
    private int employmentYear;

    @NotNull(message = "Annual Leave SHOULDN'T BE NULL")
//    @Pattern(regexp = "^[1-9]?[0-9]{1}$|^100$", message = ("input has to be numbers between1-100"))
//    @Pattern(regexp="^(0|[1-9][0-9]*)$")
    @NumberFormat(style = NumberFormat.Style.NUMBER)//has to be number
    //Cannot be null
    //it has to be number
    private int annualLeave;

}

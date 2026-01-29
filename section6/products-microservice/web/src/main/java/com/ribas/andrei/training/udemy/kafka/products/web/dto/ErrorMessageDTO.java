package com.ribas.andrei.training.udemy.kafka.products.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDTO {

    private LocalDate timestamp;
    private String message;
    private String details;
}

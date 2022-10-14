package com.example.desafio_quality.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionDetails {
    private String title, message, fields, fieldsMessage;
    private int status;
    private LocalDateTime timeStamp;
}
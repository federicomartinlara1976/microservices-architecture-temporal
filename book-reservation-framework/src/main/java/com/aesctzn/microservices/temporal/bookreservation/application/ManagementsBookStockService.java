package com.aesctzn.microservices.temporal.bookreservation.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aesctzn.microservices.temporal.bookreservation.domain.Book;

@Service
public class ManagementsBookStockService implements ManagementBookStock {
    @Override
    public ResponseEntity<String> deductStock(Book book) {

        return ResponseEntity.status(HttpStatus.OK).body("Resultado mock del servicio descuento de stock para libro+ "+book.getTitle());
    }
}

package com.aesctzn.microservices.temporal.bookreservation.adapter.inbound;

import com.aesctzn.microservices.temporal.bookreservation.application.Reservations;
import com.aesctzn.microservices.temporal.bookreservation.domain.Book;
import com.aesctzn.microservices.temporal.bookreservation.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class ReservationController {

    private List<Book> books = new ArrayList<>();

    @Autowired
    private Reservations reservationService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(books);
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveBook(@RequestBody Reservation request) {
        Book book = findBookById(request.getBook().getId());
        if (book != null) {
            return ResponseEntity.ok("Book reserved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }

    private Book findBookById(Long bookId) {
        // Lógica para buscar un libro por ID
        // Aquí podrías consultar una base de datos o un servicio externo
        // Por simplicidad, retornaremos un libro ficticio
        return new Book(1L, "Spring Boot in Action", "Craig Walls");
    }
}
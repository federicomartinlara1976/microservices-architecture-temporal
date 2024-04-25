package com.aesctzn.microservices.temporal.bookreservation.infrastructure;

import com.aesctzn.microservices.temporal.bookreservation.application.ManagementBookStock;
import com.aesctzn.microservices.temporal.bookreservation.domain.Book;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ManagementsBookStockService implements ManagementBookStock {

	@Override
	public ResponseEntity<String> deductStock(Book book) {
		// TODO Auto-generated method stub
		return null;
	}
}

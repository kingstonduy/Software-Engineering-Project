package com.petcare.rest.webservices.restful.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.rest.webservices.restful.model.BookingDate;
import com.petcare.rest.webservices.restful.model.BookingDatePosting;
import com.petcare.rest.webservices.restful.service.BookingDateService;

@RestController
public class BookingDateController {
    BookingDateService bookingDateService;

    public BookingDateController(BookingDateService bookingDateService) {
        this.bookingDateService = bookingDateService;
    }

    @GetMapping("/bookingdate/all")
    public List<BookingDate> getAllBookingDate(){
        return bookingDateService.getAllBookingDate();
    }

    @GetMapping("bookingdate/all/user/{username}")
    public List<BookingDate> getAllBookingDateByUserName(@PathVariable String username){
        return bookingDateService.getAllBookingDateByUserName(username);
    }

    @PostMapping("/bookingdate/post")
    public ResponseEntity<BookingDate> postBookingDate(@RequestBody BookingDatePosting bookingDatePosting){
        BookingDate bookingDate = bookingDateService.postBookingDate(
                bookingDatePosting.getUsername(),bookingDatePosting.getLocalDate(),
                bookingDatePosting.getPhoneNumber(),bookingDatePosting.getTypePet());
        if(bookingDate != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

package com.springboot.transaction.controller;


import com.springboot.transaction.dto.AppointmentFormDto;
import com.springboot.transaction.entity.Appointment;
import com.springboot.transaction.service.BookAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/patient")
public class BookAppointmentController {

    @Autowired
    BookAppointmentService bookAppointmentService;

    @PostMapping(value = "/bookAppointment", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody AppointmentFormDto appointmentFormDto) {
        return new ResponseEntity<Appointment>(bookAppointmentService.bookAppointment(appointmentFormDto), HttpStatus.CREATED);
    }
}

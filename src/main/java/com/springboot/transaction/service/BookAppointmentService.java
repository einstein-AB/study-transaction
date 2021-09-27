package com.springboot.transaction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.transaction.dto.AppointmentFormDto;
import com.springboot.transaction.entity.Appointment;
import com.springboot.transaction.entity.Patient;
import com.springboot.transaction.repository.AppointmentRepository;
import com.springboot.transaction.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookAppointmentService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(AppointmentFormDto appointmentFormDto) {
        /**
         * Patient form will logically include all the patient information only
         * Patient fills all the same info while booking an appointment
         * So convert the 'AppointmentFormDto' to 'Patient' as both are same
        */
        Patient patientDataToBeSaved = new ObjectMapper().convertValue(appointmentFormDto, Patient.class); //Map form to entity

        /**
         * Save the patient (operation 1/2)
         * save() method returns the same entity which is being saved
         */
        Patient patient = patientRepository.save(patientDataToBeSaved);//Save the patient info


        /**----------------------------
         * Throw some exception here
         */
        //System.out.println(1/0);


         //Save Appointment (operation 2/2)
        Appointment appointment = Appointment.builder()
                .patientId(patient.getPatientId())
                .patientName(patient.getPatientName())
                .patientAge(patient.getPatientAge())
                .mobileNumber(patient.getMobileNumber())
                .build();
        Appointment appointmentSavedData = appointmentRepository.save(appointment);
        return appointmentSavedData;
    }

}

package com.onlinevet.clinic.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlinevet.clinic.model.Appointment;

public interface AppointmentService extends CrudService<Appointment, Long> {
    List<Appointment> findAllForDateAndVet(Date date, Long vetId);

    List<Appointment> findAllForPetById(Long petId);

    Page<Appointment> findAllForPetById(Long petId, Pageable pageable);

    List<Appointment> findAllForVetById(Long vetId);

    Page<Appointment> findAllForVetById(Long vetId, Pageable pageable);

    Appointment findByDateAndVetId(Date date, Long vetId);
}

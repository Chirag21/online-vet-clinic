package com.onlinevet.clinic.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlinevet.clinic.model.Appointment;

public interface AppointmentService extends CrudService<Appointment, Long> {
    List<Appointment> findAllForDateAndVet(Date date, Long vetId);

    List<Appointment> findAllByOwnerIdOrderByDate(Long ownerId);

    Page<Appointment> findAllByOrderByDate(Pageable pageable);

    Page<Appointment> findAllByOwnerIdOrderByDate(Long ownerId, Pageable pageable);
    
    List<Appointment> findAllByPetById(Long petId);

    Page<Appointment> findAllByPetById(Long petId, Pageable pageable);

    List<Appointment> findAllByVetById(Long vetId);

    Page<Appointment> findAllByVetById(Long vetId, Pageable pageable);

    Appointment findByDateAndVetId(Date date, Long vetId);
    
    List<Appointment> findAllByMobileNo(String mobileNo);
    
    Page<Appointment> findAllByMobileNo(String mobileNo, Pageable pageable);
}

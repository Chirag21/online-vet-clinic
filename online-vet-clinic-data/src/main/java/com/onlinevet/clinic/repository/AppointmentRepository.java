package com.onlinevet.clinic.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onlinevet.clinic.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("select a from Appointment as a where a.date between ?1 and ?2 and a.vet.id = ?3")
    List<Appointment> findAllBetweenDatesByVetId(Date startDate, Date endDate, Long vetId);

    @Query("select a from Appointment as a where a.date between ?1 and ?2")
    List<Appointment> findAllBetweenDates(Date startDate, Date endDate);

    List<Appointment> findAllByOwnerIdOrderByDate(Long ownerId);

    Page<Appointment> findAllByOrderByDate(Pageable pageable);

    Page<Appointment> findAllByOwnerIdOrderByDate(Long ownerId, Pageable pageable);
    
    List<Appointment> findAllByPetIdOrderByDate(Long petId);

    Page<Appointment> findAllByPetIdOrderByDate(Long petId, Pageable pageable);

    List<Appointment> findAllByVetIdOrderByDate(Long vetId);

    Page<Appointment> findAllByVetIdOrderByDate(Long vetId, Pageable pageable);

    Appointment findOneByDateAndVetId(Date date, Long vetId);
    
    List<Appointment> findAllByMobileNo(String mobileNo);
    
    Page<Appointment> findAllByMobileNo(String mobileNo, Pageable pageable);
}

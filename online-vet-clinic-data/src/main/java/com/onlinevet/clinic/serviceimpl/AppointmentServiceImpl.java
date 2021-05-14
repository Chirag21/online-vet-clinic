package com.onlinevet.clinic.serviceimpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.exceptions.AppointmentNotFoundException;
import com.onlinevet.clinic.model.Appointment;
import com.onlinevet.clinic.repository.AppointmentRepository;
import com.onlinevet.clinic.service.AppointmentService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public Appointment findById(Long id) {
		return appointmentRepository.findById(id).orElseThrow(AppointmentNotFoundException::new);
	}

	@Override
	public Set<Appointment> findAll() {
		return appointmentRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public Appointment save(Appointment appointment) {
		return appointmentRepository.saveAndFlush(appointment);
	}

	@Override
	public void delete(Appointment appointment) {
		appointmentRepository.delete(appointment);
	}

	@Override
	public void deleteById(Long id) {
		appointmentRepository.deleteById(id);
	}

	@Override
	public List<Appointment> findAllForDateAndVet(Date date, Long vetId) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+530"));
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date endDate = calendar.getTime();
		return appointmentRepository.findAllBetweenDatesByVetId(date, endDate, vetId);
	}

	@Override
	public List<Appointment> findAllByPetById(Long petId) {
		return appointmentRepository.findAllByPetIdOrderByDate(petId);
	}

	@Override
	public Page<Appointment> findAllByPetById(Long petId, Pageable pageable) {
		Page<Appointment> appointments = appointmentRepository.findAllByPetIdOrderByDate(petId, pageable);
		return new PageImpl<>(appointments.toList(), pageable, appointments.getTotalElements());
	}

	@Override
	public List<Appointment> findAllByVetById(Long vetId) {
		return appointmentRepository.findAllByVetIdOrderByDate(vetId);
	}

	@Override
	public Page<Appointment> findAllByVetById(Long vetId, Pageable pageable) {
		Page<Appointment> appointments = appointmentRepository.findAllByVetIdOrderByDate(vetId, pageable);
		return new PageImpl<>(appointments.toList(), pageable, appointments.getTotalElements());
	}

	@Override
	public Page<Appointment> findAllByOrderByDate(Pageable pageable) {
		Page<Appointment> appointments = appointmentRepository.findAllByOrderByDate(pageable);
		return new PageImpl<>(appointments.toList(), pageable, appointments.getTotalElements());
	}

	@Override
	public Page<Appointment> findAllByMobileNo(String mobileNo, Pageable pageable) {
		return appointmentRepository.findAllByMobileNo(mobileNo,pageable);
	}


	@Override
	public Appointment findByDateAndVetId(Date date, Long vetId) {
		return appointmentRepository.findOneByDateAndVetId(date, vetId);
	}

	@Override
	public List<Appointment> findAllByOwnerIdOrderByDate(Long ownerId) {
		return appointmentRepository.findAllByOwnerIdOrderByDate(ownerId);
	}

	@Override
	public Page<Appointment> findAllByOwnerIdOrderByDate(Long ownerId, Pageable pageable) {
		return appointmentRepository.findAllByOwnerIdOrderByDate(ownerId, pageable);
	}

	@Override
	public List<Appointment> findAllByMobileNo(String mobileNo) {
		return appointmentRepository.findAllByMobileNo(mobileNo);
	}

}

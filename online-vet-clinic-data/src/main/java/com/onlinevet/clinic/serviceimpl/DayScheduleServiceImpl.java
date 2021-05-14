package com.onlinevet.clinic.serviceimpl;

import java.sql.Time;
import java.util.Set;
import java.util.stream.Collectors;

import com.onlinevet.clinic.exceptions.ScheduleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.DaySchedule;
import com.onlinevet.clinic.repository.DayScheduleRepository;
import com.onlinevet.clinic.service.DayScheduleService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class DayScheduleServiceImpl implements DayScheduleService {
	@Autowired
	private DayScheduleRepository dayScheduleRepository;
	
	@Override
	public DaySchedule findById(Long id) {
		return dayScheduleRepository.findById(id).orElseThrow(ScheduleNotFoundException::new);
	}

	@Override
	public Set<DaySchedule> findAll() {
		return dayScheduleRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public DaySchedule save(DaySchedule daySchedule) {
		// Convert from string "hh:mm" to time(hh:mm:ss)
		String startTimeFormat = daySchedule.getStartTime().toString() + (daySchedule.getStartTime().toString().length() == 5 ? ":00" : "");
        String endTimeFormat = daySchedule.getEndTime().toString()  + (daySchedule.getEndTime().toString().length() == 5 ? ":00" : "");
		daySchedule.setStartTime(Time.valueOf(startTimeFormat));
		daySchedule.setEndTime(Time.valueOf(endTimeFormat));
        return dayScheduleRepository.saveAndFlush(daySchedule);
	}

	@Override
	public void delete(DaySchedule daySchedule) {
		dayScheduleRepository.delete(daySchedule);
	}

	@Override
	public void deleteById(Long id) {
		dayScheduleRepository.deleteById(id);
	}

	@Override
	public void create(DaySchedule daySchedule) {
		dayScheduleRepository.saveAndFlush(daySchedule);
	}

}

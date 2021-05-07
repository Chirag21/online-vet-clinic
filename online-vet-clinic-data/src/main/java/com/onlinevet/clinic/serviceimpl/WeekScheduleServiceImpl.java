package com.onlinevet.clinic.serviceimpl;

import java.sql.Time;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.DayOfWeek;
import com.onlinevet.clinic.model.DaySchedule;
import com.onlinevet.clinic.model.WeekSchedule;
import com.onlinevet.clinic.repository.WeekScheduleRepository;
import com.onlinevet.clinic.service.DayScheduleService;
import com.onlinevet.clinic.service.WeekScheduleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class WeekScheduleServiceImpl implements WeekScheduleService {

	@Autowired
	private final WeekScheduleRepository weekScheduleRepository;
	
	@Autowired
	private DayScheduleService dayScheduleService;
	
	@Override
	public WeekSchedule findById(Long id) {
		return weekScheduleRepository.findById(id).orElseThrow();
	}

	@Override
	public Set<WeekSchedule> findAll() {
		return weekScheduleRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public WeekSchedule save(WeekSchedule weekSchedule) {
		weekSchedule.getDaySchedules().forEach(e -> dayScheduleService.save(e));
		return weekScheduleRepository.saveAndFlush(weekSchedule);
	}

	@Override
	public void delete(WeekSchedule weekSchedule) {
		weekScheduleRepository.delete(weekSchedule);
	}

	@Override
	public void deleteById(Long id) {
		weekScheduleRepository.deleteById(id);
	}

	@Override
	public WeekSchedule createDefault() {
        WeekSchedule weekSchedule = this.weekScheduleRepository.saveAndFlush(new WeekSchedule());
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            DaySchedule daySchedule = new DaySchedule();
            daySchedule.setDayOfWeek(dayOfWeek);
            daySchedule.setStartTime(Time.valueOf("00:00:00"));
            daySchedule.setEndTime(Time.valueOf("00:00:00"));
            daySchedule.setWeekSchedule(weekSchedule);
            this.dayScheduleService.create(daySchedule);
        }
        return weekSchedule;
	}

}

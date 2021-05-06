package com.onlinevet.clinic.service;

import com.onlinevet.clinic.model.WeekSchedule;

public interface WeekScheduleService extends CrudService<WeekSchedule, Long> {
	WeekSchedule createDefault();
}

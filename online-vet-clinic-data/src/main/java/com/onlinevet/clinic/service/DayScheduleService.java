package com.onlinevet.clinic.service;

import com.onlinevet.clinic.model.DaySchedule;

public interface DayScheduleService extends CrudService<DaySchedule, Long> {
    void create(DaySchedule daySchedule);
}

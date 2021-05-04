package com.onlinevet.clinic.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "day_schedules")
public class DaySchedule extends BaseEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1595335854559253194L;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time")
    private Time startTime;

    
   @Column(name = "end_time")
    private Time endTime;

    @ManyToOne
    @JoinColumn(name = "week_schedule_id")
    private WeekSchedule weekSchedule;

	public DaySchedule(Long id, DayOfWeek dayOfWeek, Time startTime, Time endTime, WeekSchedule weekSchedule) {
		super(id);
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
		this.weekSchedule = weekSchedule;
	}
}

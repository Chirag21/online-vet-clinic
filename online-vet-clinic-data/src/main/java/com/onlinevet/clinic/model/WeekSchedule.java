package com.onlinevet.clinic.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "week_schedules")
public class WeekSchedule implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -390221415485016265L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "duration_minutes")
    private int appointmentDuration;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "weekSchedule")
    @OrderBy("id")
    @Column(name = "day_schedules")
    @JsonManagedReference
    private Set<DaySchedule> daySchedules;

    public WeekSchedule() {
        this.setDaySchedules(new HashSet<>());
    }
}

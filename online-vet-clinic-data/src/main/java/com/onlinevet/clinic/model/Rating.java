package com.onlinevet.clinic.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ratings")
public class Rating implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rate;

    private String comment;

    private Owner ratingOwner;

    @Column(name = "date", insertable = false, updatable = false)
    private Date date;

}

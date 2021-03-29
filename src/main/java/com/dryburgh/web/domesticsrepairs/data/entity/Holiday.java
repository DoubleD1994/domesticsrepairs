package com.dryburgh.web.domesticsrepairs.data.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HOLIDAY")
public class Holiday {

	@Id
	@Column(name="HOLIDAY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long holidayId;
	
	@Column(name="ENGINEER_ID")
	private long engineerId;
		
	@Column(name="HOLIDAY_DATE")
	private LocalDate holidayDate;

	public long getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(long holidayId) {
		this.holidayId = holidayId;
	}

	public long getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(long engineerId) {
		this.engineerId = engineerId;
	}

	public LocalDate getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}

	@Override
	public String toString() {
		return "Holiday [holidayId=" + holidayId + ", engineerId=" + engineerId + ", holidatDate=" + holidayDate + "]";
	}
}

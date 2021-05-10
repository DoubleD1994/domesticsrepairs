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
		
	@Column(name="HOLIDAY_START_DATE")
	private LocalDate holidayStartDate;
	
	@Column(name="HOLIDAY_END_DATE")
	private LocalDate holidayEndDate;

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

	public LocalDate getHolidayStartDate() {
		return holidayStartDate;
	}

	public void setHolidayStartDate(LocalDate holidayStartDate) {
		this.holidayStartDate = holidayStartDate;
	}

	public LocalDate getHolidayEndDate() {
		return holidayEndDate;
	}

	public void setHolidayEndDate(LocalDate holidayEndDate) {
		this.holidayEndDate = holidayEndDate;
	}

	@Override
	public String toString() {
		return "Holiday [holidayId=" + holidayId + ", engineerId=" + engineerId + ", holidayStartDate="
				+ holidayStartDate + ", holidayEndDate=" + holidayEndDate + ", getHolidayId()=" + getHolidayId()
				+ ", getEngineerId()=" + getEngineerId() + ", getHolidayStartDate()=" + getHolidayStartDate()
				+ ", getHolidayEndDate()=" + getHolidayEndDate() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}

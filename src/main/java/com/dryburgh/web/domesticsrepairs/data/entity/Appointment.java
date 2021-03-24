package com.dryburgh.web.domesticsrepairs.data.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dryburgh.web.domesticsrepairs.business.appointments.TimeslotType;

@Entity
@Table(name="APPOINTMENT")
public class Appointment {

	
	@Id
	@Column(name="APPOINTEMT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long appointmentId;
	
	@Column(name="ENGINEER_ID")
	private long engineerId;
	
	@Column(name="CUSTOMER_NAME", nullable=false)
	private String customerName;

	@Column(name="CUSTOMER_ADDRESS", nullable=false)
	private String customerAddress;
	
	@Column(name="CUSTOMER_PHONE_NUMBER", nullable=false)
	private String customerPhoneNumber;
	
	@Column(name="CUSTOMER_EMAIL")
	private String customerEmail;
	
	@Column(name="TIMESLOT")
	private TimeslotType timeslotType;
	
	@Column(name="APPOINTMENT_DATE")
	private LocalDate appointmentDay;
	
	@Column(name="IS_COMPLETE", nullable=false)
	private boolean isComplete;
	
	@Column(name="WORK_DONE")
	private String workDone;
	
	@Column(name="CHARGE")
	private double charge;
	
	
	public Appointment(long engineerId, String customerName, String customerAddress, String customerPhoneNumber, String customerEmail,
			TimeslotType timeslot, LocalDate appointmentDate) {
		this.engineerId = engineerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmail = customerEmail;
		this.timeslotType = timeslot;
		this.appointmentDay = appointmentDate;
	}

	public long getAppointmentId() {
		return this.appointmentId;
	}
	
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public long getEngineerId() {
		return this.engineerId;
	}

	public void setEngineerId(long engineerId) {
		this.engineerId = engineerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public TimeslotType getTimeslotType() {
		return timeslotType;
	}

	public void setTimeslotType(TimeslotType timeslotType) {
		this.timeslotType = timeslotType;
	}

	public LocalDate getAppointmentDay() {
		return appointmentDay;
	}

	public void setAppointmentDay(LocalDate appointmentDay) {
		this.appointmentDay = appointmentDay;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public String getWorkDone() {
		return workDone;
	}

	public void setWorkDone(String workDone) {
		this.workDone = workDone;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", engineerId=" + engineerId + ", customerName="
				+ customerName + ", customerAddress=" + customerAddress + ", customerPhoneNumber=" + customerPhoneNumber
				+ ", customerEmail=" + customerEmail + ", timeslotType=" + timeslotType + ", appointmentDay="
				+ appointmentDay + "]";
	}
}

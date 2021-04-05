package com.dryburgh.web.domesticsrepairs.data.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dryburgh.web.domesticsrepairs.data.entity.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE Appointment a SET a.engineerId= :engineerId, a.customerName= :customerName, a.customerAddress= :customerAddress, "
			+ "a.customerPhoneNumber= :customerPhoneNumber, a.customerEmail= :customerEmail, a.timeslotType= :timeslotType, "
			+ "a.appointmentDay= :appointmentDay WHERE a.appointmentId= :appointmentId")
	void updateAppointment(@Param("appointmentId") Long appointmentId, @Param("engineerId") Long engineerId,
			@Param("customerName") String customerName, @Param("customerAddress") String customerAddress,
			@Param("customerPhoneNumber") String customerPhoneNumber, @Param("customerEmail") String customerEmail,
			@Param("timeslotType") String timeslotType, @Param("appointmentDay") LocalDate appointmentDay);

}

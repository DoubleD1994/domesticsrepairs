package com.dryburgh.web.domesticsrepairs.data.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dryburgh.web.domesticsrepairs.data.entity.Holiday;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE Holiday h SET h.holidayStartDate= :holidayStartDate, h.holidayEndDate= :holidayEndDate WHERE h.holidayId= :holidayId")
	void updateHoliday(@Param("holidayId") Long holidayId, @Param("holidayStartDate") LocalDate holidayStartDate, @Param("holidayEndDate") LocalDate holidayEndDate);
	
	@Query("SELECT h FROM Holiday h WHERE h.engineerId= :engineerId")
	Iterable<Holiday> getHolidayByEngineerId(@Param("engineerId") Long engineerId); 
	
	@Query("SELECT h FROM Holiday h WHERE h.holidayStartDate>=:startDate AND h.holidayEndDate <=:endDate")
	Iterable<Holiday> getHolidayByDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate); 
}

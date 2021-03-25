package com.dryburgh.web.domesticsrepairs.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dryburgh.web.domesticsrepairs.data.entity.Holiday;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, Long> {

}

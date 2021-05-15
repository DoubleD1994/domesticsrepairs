package com.dryburgh.web.domesticsrepairs.data.repository;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dryburgh.web.domesticsrepairs.data.entity.Engineer;

@Repository
public interface EngineerRepository extends CrudRepository<Engineer, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE Engineer e SET e.engineerName= :name, e.engineerEmail= :email, e.engineerPhoneNumber= :phoneNumber, engineerPassword= :password WHERE e.engineerId= :engineerId")
	void updateEngineer(@Param("engineerId") Long engineerId, @Param("name") String name, @Param("email") String email,
			@Param("phoneNumber") String phoneNumber, @Param("password") String password);

	@Query("SELECT e FROM Engineer e WHERE e.engineerEmail = :email")
	Engineer findEngineerByEmail(@Param("email") String engineerEmail);

}

package com.dryburgh.web.domesticsrepairs.business.engineers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dryburgh.web.domesticsrepairs.data.entity.Engineer;
import com.dryburgh.web.domesticsrepairs.data.repository.EngineerRepository;

@Service
public class EngineerService {

	private final EngineerRepository engineerRepository;

	@Autowired
	public EngineerService(EngineerRepository engineerRepository) {
		this.engineerRepository = engineerRepository;
	}

	public List<Engineer> getAllEngineers() {
		Iterable<Engineer> allEngineers = engineerRepository.findAll();
		List<Engineer> engineersList = new ArrayList<>();
		allEngineers.forEach(engineer -> {engineersList.add(engineer);});
		return engineersList;
	}

	public Engineer getEngineerByEngineerId(long engineerId) {
		return engineerRepository.findById(engineerId).get();
	}

	public void createNewEngineer(Engineer newEngineer) {
		engineerRepository.save(newEngineer);
	}

	public void updateEngineerDetails(long engineerId, Engineer updateEngineer) {
		engineerRepository.updateEngineer(engineerId, updateEngineer.getEngineerName(),
				updateEngineer.getEngineerEmail(), updateEngineer.getEngineerPhoneNumber(),
				updateEngineer.getEngineerPassword());
	}

	public void deleteEngineer(long engineerId) {
		engineerRepository.deleteById(engineerId);
	}
 
	public Engineer loginEngineerToPortal(String engineerEmail, String engineerPassword) {
		Engineer engineer = engineerRepository.findEngineerByEmail(engineerEmail);
		if(!engineer.getEngineerPassword().trim().equals(engineerPassword)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "engineer email and password mismatch");
		}
		return engineer;
	}
}

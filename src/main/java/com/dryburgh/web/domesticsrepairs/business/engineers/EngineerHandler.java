package com.dryburgh.web.domesticsrepairs.business.engineers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dryburgh.web.domesticsrepairs.data.entity.Engineer;
import com.dryburgh.web.domesticsrepairs.data.repository.EngineerRepository;

@Service
public class EngineerHandler {

	private EngineerPool engineerPool;
	private final EngineerRepository engineerRepository;
	
	@Autowired
	public EngineerHandler(EngineerRepository engineerRepository) {
		engineerPool = new EngineerPool();
		this.engineerRepository = engineerRepository;
	}
	
	public Engineer getAvailableEngineerForAppointment() {
		return engineerPool.getEngineerFromList();
	}

	public void createNewEngineer(Engineer newEngineer) {
		engineerRepository.save(newEngineer);
	}
	
	public List<Engineer> getAllEngineers(){
		Iterable<Engineer> allEngineers = engineerRepository.findAll();
		List<Engineer> engineersList = new ArrayList<>();
		allEngineers.forEach(engineer -> {
			Engineer listEngineer = new Engineer();
			listEngineer.setEngineerId(engineer.getEngineerId());
			listEngineer.setEngineerName(engineer.getEngineerName());
			listEngineer.setEngineerEmail(engineer.getEngineerEmail());
			listEngineer.setEngineerPhoneNumber(engineer.getEngineerPhoneNumber());
			listEngineer.setEngineerPassword(engineer.getEngineerPassword());
			engineersList.add(listEngineer);
		});
		return engineersList;
	}
}

package com.dryburgh.web.domesticsrepairs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dryburgh.web.domesticsrepairs.business.engineers.EngineerHandler;
import com.dryburgh.web.domesticsrepairs.data.entity.Engineer;

@RestController
@RequestMapping("/engineers")
public class EngineerController {

	private final EngineerHandler engineerHandler;
	
	@Autowired
	public EngineerController(EngineerHandler engineerHandler) {
		this.engineerHandler = engineerHandler;
	}
	
	@GetMapping
	public List<Engineer> getEngineers() {
		return engineerHandler.getAllEngineers();
	}
	
	@PostMapping
	public String createNewEngineer(@RequestBody Engineer newEngineer) {
		engineerHandler.createNewEngineer(newEngineer);
		return "New Engineer Created";
	}
}

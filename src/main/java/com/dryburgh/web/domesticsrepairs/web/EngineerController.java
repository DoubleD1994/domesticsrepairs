package com.dryburgh.web.domesticsrepairs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dryburgh.web.domesticsrepairs.business.engineers.EngineerService;
import com.dryburgh.web.domesticsrepairs.data.entity.Engineer;

@RestController
@RequestMapping("/engineers")
public class EngineerController {

	private final EngineerService engineerService;
	
	@Autowired
	public EngineerController(EngineerService engineerHandler) {
		this.engineerService = engineerHandler;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Engineer> getEngineers() {
		return engineerService.getAllEngineers();
	}
	
	@GetMapping("/{engineerId}")
	@ResponseStatus(HttpStatus.OK)
	public Engineer getEngineerByEngineerId(@PathVariable(name="engineerId") long engineerId) {
		return engineerService.getEngineerByEngineerId(engineerId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String createNewEngineer(@RequestBody Engineer newEngineer) {
		engineerService.createNewEngineer(newEngineer);
		return "New Engineer Created";
	}
	
	@PutMapping("/{engineerId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String updateEngineer(@PathVariable(name="engineerId") long engineerId, @RequestBody Engineer updateEngineer) {
		engineerService.updateEngineerDetails(engineerId, updateEngineer);
		return "Updated Engineer with ID: " + engineerId;
	}
	
	@DeleteMapping("/{engineerId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String deleteEngineer(@PathVariable(name="engineerId") long engineerId) {
		engineerService.deleteEngineer(engineerId);
		return "Engineer Deleted with ID: " + engineerId;
	}
}

package com.dryburgh.web.domesticsrepairs.business.engineers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dryburgh.web.domesticsrepairs.data.entity.Engineer;

public class EngineerPool {

	public List<Engineer> engineerList;
	
	@Autowired
	public EngineerPool() {
		engineerList = new ArrayList<>();
	}

	public void addEngineerToList(Engineer engineer) {
		engineerList.add(engineer);
	}
	
	public Engineer getEngineerFromList() {
		return engineerList.get(0);
	}
	
}

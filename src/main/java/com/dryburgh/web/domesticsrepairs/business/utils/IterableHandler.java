package com.dryburgh.web.domesticsrepairs.business.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IterableHandler<O> {

	@Autowired
	public IterableHandler(){}
	
	public List<O> addObjectToList(Iterable<O> objects) {
		List<O> list = new ArrayList<>();
		objects.forEach(object -> {list.add(object);});
		return list;
	}
	
}

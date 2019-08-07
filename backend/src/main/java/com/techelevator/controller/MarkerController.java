package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Marker;
import com.techelevator.model.MarkerDao;

@RestController
@CrossOrigin
@RequestMapping("/markers")
public class MarkerController {
	
	@Autowired
	private MarkerDao markerDao;
	
	@GetMapping
	public List<Marker> list() {
		return markerDao.getAllMarkers();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String create(@RequestBody Marker marker) {
		return "Success";
	}
}

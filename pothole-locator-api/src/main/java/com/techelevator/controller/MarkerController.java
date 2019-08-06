package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Marker;
import com.techelevator.model.MarkerDao;

@RestController
@CrossOrigin
@RequestMapping("api/markers")
public class MarkerController {
	MarkerDao markerDao;
	
	@Autowired
	public MarkerController(MarkerDao markerDao) {
		this.markerDao = markerDao;
	}
	
	@GetMapping
	public List<Marker> list() {
		return markerDao.getAllMarkers();
	}
	
	@GetMapping("{id}")
	public Marker read(@PathVariable int id) {
		Marker requestedMarker = markerDao.getMarkerById(new Long(id));
		
		return requestedMarker;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Marker create(@RequestBody Marker marker) {
		return markerDao.saveMarker(marker);
	}
	
	@PutMapping("{id}")
	public Marker update(@PathVariable int id, @RequestBody Marker marker) {
		Marker requestedMarker = markerDao.saveMarker(marker);
		
		return requestedMarker;
	}
}

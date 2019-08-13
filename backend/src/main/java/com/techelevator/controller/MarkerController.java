package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.techelevator.markers.exception.MarkerIdNotFound;
import com.techelevator.model.Marker;
import com.techelevator.model.MarkerDao;



@RestController
@CrossOrigin
@RequestMapping("/markers")
public class MarkerController {
	
	 @DeleteMapping("/{id}")
	    public boolean delete(@PathVariable long id) {
		 Marker requestedMarker = markerDao.read(id);
			
			if(requestedMarker != null) {
				markerDao.delete(id);
				return true;
			} else {
				throw new MarkerIdNotFound(id);

			}	
	    }
	
	 @GetMapping("/{id}")
	    public Marker read(@PathVariable long id) {
		 Marker requestedMarker = markerDao.read(id);
			
			if(requestedMarker != null) {
				return requestedMarker;
			} else {
				throw new MarkerIdNotFound(id);

			}	
	    }
	
	@Autowired
	private MarkerDao markerDao;
	
	@GetMapping
	public List<Marker> list() {
		return markerDao.getAllMarkers();
	}
	
	@GetMapping("/active")
	public List<Marker> activeList() {
		return markerDao.getActiveMarkers();
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Marker create(@RequestBody Marker marker) {
		return markerDao.create(marker );
	}
	
	@PutMapping("/{id}")
	public Marker update(@PathVariable long id, @RequestBody Marker marker) {
		if(markerDao.getMarkerById(id) != null) {
			markerDao.update(id, marker);
		}
		
		return markerDao.getMarkerById(id);
		
	}
}

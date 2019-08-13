package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface MarkerDao {
	public List<Marker> getAllMarkers();
	
	public List<Marker> getActiveMarkers();
	
	public Marker getMarkerById(long id);
	
	public List<Marker> getMarkersByUserId(long userId);
	
	public Marker create(Marker marker);
		
	public void update(long id, Marker marker);
		
	public void delete(long id);

	public Marker read(long id);

}

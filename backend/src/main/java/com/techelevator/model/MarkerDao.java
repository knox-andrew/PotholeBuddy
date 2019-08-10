package com.techelevator.model;

import java.util.List;

public interface MarkerDao {
	public List<Marker> getAllMarkers();
	
	public Marker getMarkerById(long id);
	
	public List<Marker> getMarkersByUserId(long userId);
	
	public Marker create(long user_id, double latitude, double longitude, String rating, String comments);
		
	public Marker update(long id, long userId, double latitude, double longitude, String rating, String comments);
}

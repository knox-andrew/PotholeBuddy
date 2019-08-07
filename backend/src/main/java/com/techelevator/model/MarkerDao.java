package com.techelevator.model;

import java.util.List;

public interface MarkerDao {
	public List<Marker> getAllMarkers();
	
	public Marker getMarkerById(long id);
	
	public Marker create(long user_id, String latitude, String longitude, String rating, String comments);
		
	public Marker update(long id, long userId, String latitude, String longitude, String rating, String comments);
}

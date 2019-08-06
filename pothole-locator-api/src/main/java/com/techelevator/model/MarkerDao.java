package com.techelevator.model;

import java.util.List;

public interface MarkerDao {
	public List<Marker> getAllMarkers();
	public Marker getMarkerById(Long id);
	public List<Marker> getMarkersByUserId(Long userId);
	public Marker saveMarker(Marker marker);
}

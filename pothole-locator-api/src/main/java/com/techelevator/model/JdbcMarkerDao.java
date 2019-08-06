package com.techelevator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;


@Component
public class JdbcMarkerDao implements MarkerDao {
	
	private Map<Long, Marker> markers = new HashMap<>();
	
	public JdbcMarkerDao() {
		
		Random rand = new Random();
		for(int i = 0; i < 5; i++) {
			Marker m = new Marker();
			m.setId(i);
			m.setLatitude(Double.toString(rand.nextDouble()));
			m.setLongitude(Double.toString(rand.nextDouble()));
			m.setRating(Integer.toString(rand.nextInt() * 5));
			m.setComments("");
			m.setUserId(i);
			
			markers.put(m.getId(), m);
		}
	}	
	
	@Override
	public List<Marker> getAllMarkers() {
		// TODO Auto-generated method stub
		return new ArrayList<Marker>(markers.values());
	}

	@Override
	public Marker getMarkerById(Long id) {
		return markers.get(id);
	}

	@Override
	public List<Marker> getMarkersByUserId(Long userId) {
		List<Marker> mList = new ArrayList<>();
		for(Long id : markers.keySet()) {
			if(markers.get(id).getUserId() == userId) {
				mList.add(markers.get(id));
			}
		}
		return mList;
	}

	@Override
	public Marker saveMarker(Marker marker) {
		if(markers.get(marker.getId())  == null) {
			markers.put(marker.getId(), marker);
		}
		
		return marker;
	}

}


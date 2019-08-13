package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcMarkerDao implements MarkerDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcMarkerDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Marker> getAllMarkers() {
		List<Marker> markers = new ArrayList<>();
		
		String sql = "SELECT id, user_id, username, report_date, repair_date, latitude, longitude, rating, comments "
				+ "FROM markers";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while(results.next()) {
			markers.add(mapRowToMarker(results));
		}
		
		return markers;
	}
	
	@Override
	public List<Marker> getActiveMarkers() {
		List<Marker> markers = new ArrayList<>();
		
		String sql = "SELECT id, user_id, username, report_date, repair_date, latitude, longitude, rating, comments "
				+ "FROM markers "
				+ "WHERE repair_date < CURRENT_DATE";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			markers.add(this.mapRowToMarker(results));
		}
		
		return markers;
	}

	@Override
	public Marker getMarkerById(long id) {
		Marker m = null;
		
		String sql = "SELECT id, user_id, username, report_date, repair_date, latitude, longitude, rating, comments "
				+ "FROM markers "
				+ "WHERE id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		
		if(results.next()) {
			m = mapRowToMarker(results);
		}
		
		return m;
	}
	
	@Override
	public List<Marker> getMarkersByUserId(long userId) {
		List<Marker> markers = new ArrayList<>();
		
		String sql = "SELECT id, user_id, username, report_date, latitude, longitude, rating, comments "
				+ "FROM markers "
				+ "WHERE user_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		while(results.next()) {
			markers.add(mapRowToMarker(results));
		}
		
		return markers;
	}
	
	@Override
	public Marker create(Marker marker) {
		
		String sql = "INSERT INTO markers (user_id, username, latitude, longitude, rating, comments) "
				+ "VALUES(?, ?, ?, ?, ?, ?) RETURNING id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, marker.getUserId(), marker.getUserName(), 
				marker.getLatitude(), marker.getLongitude(), marker.getRating(), marker.getComments());
		if(results.next()) {
			marker.setId(results.getLong("id"));
		}
		
		return marker;
	}
	
	@Override
	public void update(long id, Marker marker) {
		String sql = "UPDATE markers "
				+ "SET user_id = ?, username = ?, report_date = ?, repair_date = ?, latitude = ?, longitude = ?, rating = ?, comments = ? "
				+ "WHERE id = ?";
		
		jdbcTemplate.update(sql, marker.getUserId(), marker.getUserName(), marker.getReportDate(), marker.getRepairDate(), 
				Double.toString(marker.getLatitude()), Double.toString(marker.getLongitude()), marker.getRating(), marker.getComments(), id);
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM markers WHERE id = ?";
		jdbcTemplate.update(sql, id);

	}

	@Override
	public Marker read(long id) {
		String sql = "SELECT id, user_id, username, report_date, repair_date, latitude, longitude, rating, comments FROM markers WHERE id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()) {
        	return this.mapRowToMarker(results);
        }
        return null;
	}
	
	private Marker mapRowToMarker(SqlRowSet row) {
		Marker m = new Marker();
		m.setId(row.getLong("id"));
		m.setUserId(row.getLong("user_id"));
		m.setUserName(row.getString("username"));
		m.setReportDate(row.getDate("report_date").toLocalDate());
		if(row.getObject("repair_date") == null) {
			m.setRepairDate(null);
		} else {
			m.setRepairDate(row.getDate("repair_date").toLocalDate());
		}
		m.setLatitude(Double.parseDouble(row.getString("latitude")));
		m.setLongitude(Double.parseDouble(row.getString("longitude")));
		m.setRating(row.getString("rating"));
		m.setComments(row.getString("comments"));
		
		return m;
	}
}

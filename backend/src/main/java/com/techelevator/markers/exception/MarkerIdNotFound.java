package com.techelevator.markers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class MarkerIdNotFound extends RuntimeException {
	
private static final long serialVersionUID = 6974644855556556894L;
	
	private long id;
	
	public MarkerIdNotFound(long id) {
		super("Marker Not Found!");
		setId(id);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
